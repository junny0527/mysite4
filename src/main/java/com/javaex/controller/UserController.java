package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	// 필드
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserComtroller.logout()");

		//세션의 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();//초기화
		
		return "redirect:/main";
	}
	// 회원정보 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>modify()");

		userService.modify(userVo);
		
		UserVo authUser = new UserVo(userVo.getNo(),userVo.getName()); 
		
		session.setAttribute("authUser", authUser);

		return "redirect:/main";

	}

	// 회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model,HttpSession session) {
		System.out.println("UserController>modifyForm()");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int no =authUser.getNo();
		UserVo userVo = userService.modifyForm(no);
		model.addAttribute("userVo", userVo);
		return "/user/modifyForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>login()");

		// 세션에저장
		UserVo authUser = userService.login(userVo);
		if (authUser != null) { // 로그인성공

			session.setAttribute("authUser", authUser);
			return "redirect:/main";

		} else {// 로그인실패
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}
	}

	// 로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController>loginForm()");

		return "user/loginForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>join()");

		int count = userService.join(userVo);
		System.out.println("UserController: " + count);

		return "user/joinOk";
	}

	// 회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController>joinForm()");

		return "user/joinForm";

	}
}
