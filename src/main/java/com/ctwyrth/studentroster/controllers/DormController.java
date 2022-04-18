package com.ctwyrth.studentroster.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.ctwyrth.studentroster.models.Dorm;
import com.ctwyrth.studentroster.models.Student;
import com.ctwyrth.studentroster.services.DormService;
import com.ctwyrth.studentroster.services.StudentService;

@Controller
public class DormController {
    // -----------------------variables-----------------------
    @Autowired
    private DormService dormService;
    
    @Autowired
    private StudentService studentService;
    

    // show all
    @GetMapping("/dorms")
    public String index(Model model) {
    	List<Dorm> dorms = dormService.allDorms();
    	model.addAttribute("dorms", dorms);
        return "/dorms/index.jsp";
    }
    
    // create one
    @GetMapping("/dorms/new")
    public String addDorm(@ModelAttribute("dorm") Dorm dorm) {
    	return "/dorms/newdorm.jsp";
    }
    @PostMapping("/dorms/new")
    public String create(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/dorms/index.jsp";
    	} else {
    		dormService.createDorm(dorm);
    		return "redirect:/dorms";
    	}
    }
    
    // create a dorm from the url
    @GetMapping("/dorms/create")
    public String create(@RequestParam(value="name", required=false) String name) {
    	Dorm dorm = new Dorm(name);
    	dormService.createDorm(dorm);
    	return "redirect:/dorms";
    }

	// add student to specific dorm by url
	@GetMapping("/dorms/{id}/add")
	public String addStudentToDorm(@PathVariable("id") Long id, @RequestParam(value="student", required=false) Long student) {
		Dorm d = dormService.findDorm(id);
		d.displayDorm();
		Student addStudent = studentService.findStudent(student);
		System.out.println(addStudent);
		List<Student> temp = d.getStudents();
		temp.add(addStudent);
		d.setStudents(temp);
		System.out.println(temp);
		dormService.updateDorm(d);
		System.out.println(d.getStudents());
		return "redirect:/dorms";
	}

    // display one found by id
    @GetMapping("/dorms/{id}")
    public String showOnedormById(@PathVariable("id") Long id, Model model) {
    	List<Student> studentsWithoutADorm = studentService.allStudentsWithoutADorm();
        Dorm dormToShow = dormService.findDorm(id);
        model.addAttribute("dorm", dormToShow);
        model.addAttribute("homelessStudents", studentsWithoutADorm);
        dormToShow.displayDorm();
        return "/dorms/show.jsp";
    }
    @PutMapping("/dorms/{id}/addstudent")
    public String addStudToDorm(@PathVariable("id") Long id, @RequestParam("student") Long student_id) {
    	Dorm dormForS = dormService.findDorm(id);
    	Student addStudent = studentService.findStudent(student_id);
    	List<Student> temp = dormForS.getStudents();
    	temp.add(addStudent);
    	dormForS.setStudents(temp);
    	dormService.updateDorm(dormForS);
    	return "redirect:/dorms";
    }
    
    // update one found by id
    @GetMapping("/dorms/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
    	Dorm dormToShow = dormService.findDorm(id);
    	model.addAttribute("dorm", dormToShow);
    	return "/dorms/edit.jsp";
    }
    @PutMapping("/dorms/{id}")
    public String update(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result) {
    	if (result.hasErrors()) {
        	return "/dorms/edit.jsp";
    	} else {
    		dormService.updateDorm(dorm);
    		return "redirect:/dorms";
    	}
    }

    // delete one
    @DeleteMapping("/dorms/{id}")
    public String destroy(@PathVariable("id") Long id) {
        dormService.deleteDorm(id);
        return "redirect:/dorms";
    }
    
    // remove student from dorm
    @GetMapping("/dorm/{id}/remove")
    public String removeStudentFromDorm(@PathVariable("id") Long id, @RequestParam(value="student", required=false) Long student) {
    	Dorm dorm = dormService.findDorm(id);
//    	Student remStudent = ;
    	dorm.removeStudent(studentService.findStudent(student));
    	return "/dorms";
    }
    
}