package com.ssafy.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.mvc.model.dto.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "/board/list";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user, HttpSession session) {
		//원래는 UserService를 통해서 로그인 하는게 맞아!
		//id : ssafy, pw : 1234 통과 (유일한 회원이야)
		if(user.getId().equals("ssafy") && user.getPw().equals("1234")) {
			//이건 통과
			session.setAttribute("loginUser", user.getId());
			return "redirect:list";
		}
		///아래 코드가 동작 한다는 것은.... 
		//로그인에 실패했다는 뜻
		return "redirect:login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//로그아웃을 하는 방법 2가지!
		//1. 세션에서 유저 속성을 지우는 작업
//		session.removeAttribute("loginUser");
		//2. 세션 자체를 초기화
		session.invalidate(); 
		return "redirect:/";
	}
	
}	
