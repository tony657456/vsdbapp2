package com.korea.dbapp.test;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;

@RestController
public class UserApiControllerTest {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;

	// 의존성 주입(DI)
	public UserApiControllerTest(UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	@PostMapping("/test/user")
	public String save(User user) {
		userRepository.save(user);
		return "save ok";
	}

	@GetMapping("/test/user")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	// http://localhost:8000/user/2
	@GetMapping("/test/user/{id}")
	public String findById(@PathVariable int id) {
		User userEntity = userRepository.findById(id).get();
		String title = userEntity.getPosts().get(0).getTitle();
		return "ok";
	}
	
	@GetMapping("/test/user/username/{username}")
	public User findByUsername(@PathVariable String username) {
		return userRepository.mFindByUsername(username);
	}
	
//	@PostMapping("/login")
//	public String Login(String username, String password) {
//		User user = userRepository.mLogin(username, password);
//		if(user == null) {
//			return "login fail";
//		} else {
//			return "login success";
//		}
//	}

	
	@PostMapping("/test/login")
	public String Login(@RequestBody User user) {
		User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
		if(userEntity == null) {
			return "login fail";
		} else {
			return "login success";
		}
	}
	
	@DeleteMapping("/test/user/{id}")
	public String deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		return "delete.ok";
	}
	
	
	// user만 들고와서 수정을 하게 되면 username이 null값으로 초기화 되기 때문에 값이 다 날란간다.
	// 그래서 먼저 select를 해서 값을 가져온 다음에 수정하고 싶은 값을 수정해줘야 한다.
	@PutMapping("/user/{id}")
	public String updateOne(@PathVariable int id, User user) { // password, email
		// 1. id로 select 하기
		User userEntity = userRepository.findById(id).get();
		
		// 2. 받은 body 데이터로 수정하기
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		
		// 3. save하면 된다. (이때 꼭 id값이 db에 존재해야 update가 된다)
		userRepository.save(userEntity); // id값이 들어오면 수정하고 안들어오면 안한다.
		
		return "update.ok";
	}
}
	
























