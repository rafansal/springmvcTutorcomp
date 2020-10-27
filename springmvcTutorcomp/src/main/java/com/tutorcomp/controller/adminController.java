package com.tutorcomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorcomp.entity.Student;
import com.tutorcomp.service.AdminService;


@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/studentsList")
    public String listCustomers(Model theModel) {
		System.out.println("adminController :: listCustomers :: start");
        List < Student > theStudents = adminService.getStudents();
        theModel.addAttribute("students", theStudents);
        System.out.println("adminController :: listCustomers :: end");
        return "list-students";
    }
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
		System.out.println("adminController :: showFormForAdd :: start");
		
        Student theStudent = new Student();
        theModel.addAttribute("student", theStudent);
        
        System.out.println("adminController :: showFormForAdd :: end");
        return "student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student theStudent) {
        System.out.println("adminController :: saveStudent :: start");
        adminService.saveStudent(theStudent);
        System.out.println("adminController :: saveStudent :: end");
        return "redirect:/admin/studentsList";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("studentId") int theId,
        Model theModel) {
        System.out.println("adminController :: showFormForUpdate :: start");
        Student theStudent = adminService.getStudent(theId);
        theModel.addAttribute("student", theStudent);
        System.out.println("adminController :: showFormForUpdate :: end");
        return "student-form";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int theId) {
        System.out.println("adminController :: deleteStudent :: start");
        adminService.deleteStudent(theId);
        System.out.println("adminController :: deleteStudent :: end");
        return "redirect:/admin/studentsList";
    }
	
}
