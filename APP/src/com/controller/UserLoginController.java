package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.BackendUser;
import com.service.BackendUserService;
import com.singleton.Constants;

@Controller
@RequestMapping(value="/manager")
public class UserLoginController {
	private Logger logger=Logger.getLogger(UserLoginController.class);
	
	@Resource
	private BackendUserService backendUserService;
	
	@RequestMapping(value="/login")
	public String login() {
		return "backendlogin";
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,HttpServletRequest request) {
		BackendUser backendUser=null;
		try {
			backendUser=backendUserService.login(userCode,userPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null!=backendUser) {
			session.setAttribute(Constants.DEV_USER_SESSION,backendUser);
			return "redirect:/manager/backend/main";
		}else {
			request.setAttribute("error", "用户名或密码不正确");
			return "backendlogin";
		}
	}
	@RequestMapping(value="/backend/main")
	public String main(HttpSession session) {
		if (session.getAttribute(Constants.DEV_USER_SESSION)==null) {
			return "redirect:/manager/login";
		}
		return "backend/main";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.DEV_USER_SESSION);
		return "backendlogin";
	}
}
