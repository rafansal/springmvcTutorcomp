package com.tutorcomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.tutorcomp.entity.User;
import com.tutorcomp.service.LoginService;

@Controller
public class LoginController {

	@Autowired
    private LoginService loginService;
	
	@GetMapping("/login")
	public String login(Model theModel) {
		System.out.println("LoginController :: login :: start");
		User user = new User();
        theModel.addAttribute("user", user);
		System.out.println("LoginController :: login :: end");
		return "login";
	}
	
	@GetMapping("/loginAction")
    public String saveCustomer(@ModelAttribute("user") User user,Model theModel) {
		int roleId = loginService.checkLogin(user);
		
		if(roleId == 0)
			 return "adminHome";
		else if(roleId == 1)
			return "studentHome";
		else if (roleId == 2)
			return "tutorHome";
		User userdto = new User();
        theModel.addAttribute("user", userdto);
        return "loginfailed";
    }
}
