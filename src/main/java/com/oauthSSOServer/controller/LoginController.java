package com.oauthSSOServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mylogin")
public class LoginController {
	
	@RequestMapping("/page")
	public String loginPage() {
		return "myLogin";
	}
	
}
