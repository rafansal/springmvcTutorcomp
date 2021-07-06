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

import com.tutorcomp.entity.Seminar;
import com.tutorcomp.entity.Student;
import com.tutorcomp.entity.Tutor;
import com.tutorcomp.service.AdminService;


@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/studentsList")
    public String listStudent(Model theModel) {
		System.out.println("adminController :: listStudent :: start");
        List < Student > theStudents = adminService.getStudents();
        theModel.addAttribute("students", theStudents);
        System.out.println("adminController :: listStudent :: end");
        return "list-students";
    }
	
	@GetMapping("/showFormStudent")
    public String showFormForAddStudent(Model theModel) {
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

    @GetMapping("/updateFormStudent")
    public String showFormForUpdateStudent(@RequestParam("studentId") int theId,
        Model theModel) {
        System.out.println("adminController :: showFormForUpdate :: start");
        Student theStudent = adminService.getStudent(theId);
        theModel.addAttribute("student", theStudent);
        System.out.println("adminController :: showFormForUpdate :: end");
        return "student-form";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") int theId) {
        System.out.println("adminController :: deleteStudent :: start");
        adminService.deleteStudent(theId);
        System.out.println("adminController :: deleteStudent :: end");
        return "redirect:/admin/studentsList";
    }
    
//------------------------------------------------------------------------------------/
    
    @GetMapping("/tutorsList")
    public String listCustomers(Model theModel) {
		System.out.println("adminController :: listCustomers :: start");
        List < Tutor > theTutors = adminService.getTutors();
        theModel.addAttribute("tutors", theTutors);
        System.out.println("adminController :: listCustomers :: end");
        return "list-tutors";
    }
	
	@GetMapping("/showFormTutor")
    public String showFormForAddTutor(Model theModel) {
		System.out.println("adminController :: showFormForAdd :: start");
		
        Tutor theTutor = new Tutor();
        theModel.addAttribute("tutor", theTutor);
        
        System.out.println("adminController :: showFormForAdd :: end");
        return "tutor-form";
    }

    @PostMapping("/saveTutor")
    public String saveTutor(@ModelAttribute("tutor") Tutor theTutor) {
        System.out.println("adminController :: saveTutor :: start");
        adminService.saveTutor(theTutor);
        System.out.println("adminController :: saveTutor :: end");
        return "redirect:/admin/tutorsList";
    }

    @GetMapping("/updateFormTutor")
    public String showFormForUpdateTutor(@RequestParam("tutorId") int theId,
        Model theModel) {
        System.out.println("adminController :: showFormForUpdate :: start");
        Tutor theTutor = adminService.getTutor(theId);
        theModel.addAttribute("tutor", theTutor);
        System.out.println("adminController :: showFormForUpdate :: end");
        return "tutor-form";
    }

    @GetMapping("/deleteTutor")
    public String deleteTutor(@RequestParam("tutorId") int theId) {
        System.out.println("adminController :: deleteTutor :: start");
        adminService.deleteTutor(theId);
        System.out.println("adminController :: deleteTutor :: end");
        return "redirect:/admin/tutorsList";
    }
    
    //-------------------------------------------------------------------------------/
    
    @GetMapping("/seminarsList")
    public String listSeminars(Model theModel) {
		System.out.println("adminController :: listCustomers :: start");
        List < Seminar > theSeminars = adminService.getSeminars();
        theModel.addAttribute("seminars", theSeminars);
        System.out.println("adminController :: listCustomers :: end");
        return "list-seminars";
    }
	
	@GetMapping("/showFormSeminar")
    public String showFormForAddSeminar(Model theModel) {
		System.out.println("adminController :: showFormForAdd :: start");
		
        Seminar theSeminar = new Seminar();
        theModel.addAttribute("student", adminService.getStudents());
        theModel.addAttribute("seminar", theSeminar);
        
        System.out.println("adminController :: showFormForAdd :: end");
        return "seminar-form";
    }

    @PostMapping("/saveSeminar")
    public String saveSeminar(@ModelAttribute("seminar") Seminar theSeminar) {
        System.out.println("adminController :: saveSeminar :: start");
        adminService.saveSeminar(theSeminar);
        System.out.println("adminController :: saveSeminar :: end");
        return "redirect:/admin/seminarsList";
    }

	//heloo
    @GetMapping("/deleteSeminar")
    public String deleteSeminar(@RequestParam("seminarId") int theId) {
        System.out.println("adminController :: deleteSeminar :: start");
        adminService.deleteSeminar(theId);
        System.out.println("adminController :: deleteSeminar :: end");
        return "redirect:/admin/seminarsList";
    }
}
