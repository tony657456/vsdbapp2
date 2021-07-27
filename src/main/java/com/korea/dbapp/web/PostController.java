package com.korea.dbapp.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.comment.Comment;
import com.korea.dbapp.domain.comment.CommentRepository;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import lombok.RequiredArgsConstructor;

// final 붙어 있는 객체들은 다 생성자를 만들어 준다.
@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostRepository postRepository;
	private final HttpSession session;
	private final CommentRepository commentRepository;

	// 페이징을 할 때는 마지막 페이지를 알아야 한다.
	// 하지만 스플링에서는 last라고 해서마지막 페이지를 다 구해준다.
	// int는 null이 없기 때문에 Interger로 해준다.
	@GetMapping({ "/", "/post" })
	public String list(Model model, Integer page) { // model = request
		if (page == null) {
			page = 0;
		}
		model.addAttribute("postsEntity", postRepository.findAll(PageRequest.of(page, 4)));
		return "post/list"; // ViewResolver + RequestDispacher 기법
	}

	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		Post postEntity = postRepository.findById(id).get();
		model.addAttribute("postEntity", postEntity);

		List<Comment> commentsEntity = commentRepository.mFindAllByPostId(id);
		model.addAttribute("commentsEntity", commentsEntity);
		return "post/detail";
	}

	@DeleteMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		// 1. 권한 체크 (post id를 통해 user id를 찾아서 session의 id를 비교) - 생략

		// 세션의 user id 찾기 (session)
		// int userId = ((User)session.getAttribute("principal")).getId(); 이중괄호를 하기전에는
		// object 타입이지만 하고나면 User타입이 된다.
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();

		Post postEntity = postRepository.findById(id).get(); // 이런건 어떻게 처리하지?
		int postUserId = postEntity.getUser().getId();

		if (userId == postUserId) {
			postRepository.deleteById(id);
			return "ok";
		} else {
			return "fail";
		}

	} // end deleteById

	@GetMapping("/post/saveForm")
	public String saveForm() {
		// 1. 인증 체크 - 숙제
		return "post/saveForm";
	}

	@PostMapping("/post")
	public String save(Post post) {
		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			return "redirect:/auth/loginForm";
		}

		post.setUser(principal);
		postRepository.save(post);
		return "redirect:/";

	}

	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();

		Post postEntity = postRepository.findById(id).get();
		int postOwnerId = postEntity.getUser().getId();

		if (userId == postOwnerId) {
			model.addAttribute("postEntity", postEntity);

			return "post/updateForm";

		} else {
			return "redirect:/auth/loginForm";
		}
	}

	// 데이터를 리턴해야 하니까 @ResponseBody 붙혀야 한다.
	@PutMapping("/post/{id}")
	public @ResponseBody String update(@PathVariable int id, @RequestBody Post post) {
		Post postEntity = postRepository.findById(id).get();
		postEntity.setTitle(post.getTitle());
		postEntity.setContent(post.getContent());
		postRepository.save(postEntity);
		return "ok";
	}
}
