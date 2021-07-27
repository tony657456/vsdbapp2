package com.korea.dbapp.web;

import org.junit.jupiter.api.Test;

public class UserApiControllerTest {

	//@Test
	public void 주소파싱() {
		String addr = "http://localhost:8000/user/2";
		String host = "http://localhost:8000/user";
		System.out.println("주소 파싱 테스트");
		
		String[] result = addr.split("/");
		System.out.println(result[4]);
	}
	
	@Test
	public void 서브스트링() {
		String addr = "{2}";
		int startIndex = addr.indexOf("{");
		int endIndex = addr.indexOf("}");
		System.out.println(startIndex);
		String result = addr.substring(startIndex+1, endIndex); // 마지막 전까지 찾는게 subString
		System.out.println(result);
	}
}
