package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;
import com.korea.dbapp.util.Script;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	public UserController(UserRepository userRepository, HttpSession session) {
		super();
		this.userRepository = userRepository;
		this.session = session;
	}

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "auth/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@PostMapping("/auth/join")
	public String join(User user) {
		userRepository.save(user);
		return "redirect:/auth/loginForm";
	}
	
	@PostMapping("/auth/login")
	public @ResponseBody String login(User user) {
		User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
		if(userEntity == null) {
			
			return Script.back("login fail");
			
		} else {
			
			session.setAttribute("principal", userEntity);
			return Script.href("/");
		}
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		// 1. 인증과 권한을 검사해야 함
		// 2. 세션값 사용하면 됨.
		return "user/updateForm";
	}
	
	@PostMapping("/user/{id}") // 원래는 put으로 해야한다. 나중에 자바스크립트로 put 요청하기!!
	public String update(@PathVariable int id, String password, String address) {
		// 공통관심사
		// 회원정보를 변경해서 데이터베이스를 수정하는거 보다
		// 인증이나 권한 같은 반복적인 작업들을 매번 적기에는 비효율적이다.
		// 그래서 AOP(Aspect Oriented Programming)를 사용하는것이다.
		// User로 형변환을 해주는 이유는 session.getAttribute에 무슨 값을 받을지
		// 모르기 때문에 다운캐스팅을 해줘야 하는데 나는 getId()가 어느 객체에 있는지
		// 알기 때문에 User로 다운캐스팅을 해준것이다.
		User principal = (User) session.getAttribute("principal");

			if(principal != null && id == principal.getId()) { // 권한 ok  
				User userEntity = userRepository.findById(id).get();
				userEntity.setPassword(password);
				userEntity.setAddress(address);
				userRepository.save(userEntity);
				session.setAttribute("principal", userEntity);
				return "redirect:/";
			}
			
			return "redirect:/auth/loginForm";
		}
	
	@GetMapping("/juso")
	public String juso() {
		return "juso/jusoPopup";
	}
	
	@PostMapping("/juso")
	public String jusoResponse(String roadFullAddr, String inputYn, Model model) {
		System.out.println("주소: "+inputYn);
		System.out.println("주소: "+roadFullAddr);
		model.addAttribute("roadFullAddr", roadFullAddr);
		model.addAttribute("inputYn", inputYn);
		return "juso/jusoPopup";
	}
	
}
