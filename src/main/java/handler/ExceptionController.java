package handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// @ControllerAdvice 어노테이션을 붙이면 예외처리의 모든게 이 클래스로 들어온다.
@ControllerAdvice
@RestController
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String test1(Exception e) {
		return "에러발생: " + e.getMessage(); 
	}
}
