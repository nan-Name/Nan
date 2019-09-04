package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.DevUser;
import com.service.DevUserService;
import com.singleton.Constants;
@Controller
@RequestMapping(value="/dev")
public class DevLoginController {
	private Logger logger = Logger.getLogger(DevLoginController.class);
	
	@Resource
	private DevUserService devUserService;
	
	@RequestMapping(value="/login")
	public String login(){
		return "devlogin";
	}
	
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam String devCode,@RequestParam String devPassword,HttpServletRequest request,HttpSession session){
		DevUser user = null;
		try {
			user = devUserService.login(devCode,devPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != user){
			session.setAttribute(Constants.DEV_USER_SESSION, user);
			return "redirect:/dev/flatform/main";
		}else{
			request.setAttribute("error", "用户名或密码不正确");
			return "devlogin";
		}
	}
	
	@RequestMapping(value="/flatform/main")
	public String main(HttpSession session){
		if(session.getAttribute(Constants.DEV_USER_SESSION) == null){
			return "redirect:/dev/login";
		}
		return "developer/main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.DEV_USER_SESSION);
		return "devlogin";
	}
}

