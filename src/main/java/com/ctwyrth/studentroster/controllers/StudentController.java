package com.ctwyrth.studentroster.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ctwyrth.studentroster.models.Student;
import com.ctwyrth.studentroster.services.StudentService;

@Controller
public class StudentController {
    // -----------------------variables-----------------------
    @Autowired
    private StudentService studentService;

    // create new
    @GetMapping("/students")
    public String index(@ModelAttribute("student") Student student) {
        return "/students/newstudent.jsp";
    }
    @PostMapping("/students")
    public String create(@Valid @ModelAttribute("student") Student student, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/students/newstudent.jsp";
    	} else {
    		studentService.createStudent(student);
    		return "redirect:/students";
    	}
    }

    // display one found by id
    @GetMapping("/students/{id}")
    public String showOnestudentById(@PathVariable("id") Long id, Model model) {
        Student studentToShow = studentService.findStudent(id);
	model.addAttribute("student", studentToShow);
	return "/students/show.jsp";
    }
    
    // update one found by id
    @GetMapping("/students/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
    	Student studentToShow = studentService.findStudent(id);
    	model.addAttribute("student", studentToShow);
    	return "/students/edit.jsp";
    }
    @PutMapping("/students/{id}")
    public String update(@Valid @ModelAttribute("student") Student student, BindingResult result) {
	if (result.hasErrors()) {
        	return "/students/edit.jsp";
		} else {
		studentService.updateStudent(student);
		return "redirect:/students";
		}
    }
    
    // delete one
    @DeleteMapping("/students/{id}")
    public String destroy(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
	return "redirect:/students";
    }
}