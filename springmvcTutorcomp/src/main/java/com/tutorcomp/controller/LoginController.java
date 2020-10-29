package com.tutorcomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tutorcomp.entity.Seminar;
import com.tutorcomp.entity.User;
import com.tutorcomp.service.LoginService;
import com.tutorcomp.service.StudentService;
import com.tutorcomp.service.TutorService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TutorService tutorService;

	@GetMapping("/login")
	public String login(Model theModel) {
		System.out.println("LoginController :: login :: start");
		User user = new User();
		theModel.addAttribute("user", user);
		System.out.println("LoginController :: login :: end");
		return "login";
	}

	@PostMapping("/loginAction")
	public String saveCustomer(@ModelAttribute("user") User user, Model theModel) {
		User useDB = loginService.checkLogin(user);
		if (useDB.getRole() == 0)
			return "adminHome";
		else if (useDB.getRole() == 1) {
			List<Seminar> theSeminars = studentService.getStudentSeminar(useDB.getId());
			theModel.addAttribute("seminars", theSeminars);
			return "studentHome";
		} else if (useDB.getRole() == 2) {
			List<Seminar> theSeminars = tutorService.getTutorSeminar(useDB.getId());
			theModel.addAttribute("seminars", theSeminars);
			return "tutorHome";
		}

		User userdto = new User();
		theModel.addAttribute("user", userdto);
		return "loginfailed";
	}
}
