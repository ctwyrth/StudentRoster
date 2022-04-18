package com.ctwyrth.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctwyrth.studentroster.models.Student;
import com.ctwyrth.studentroster.repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    // shows all
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }
    
    // retrieve all without dorms
	public List<Student> allStudentsWithoutADorm() {
    	List<Student> allStudents = studentRepository.findAll();
    	for (int i = 0; i < allStudents.size(); i++) {
    		Student temp = allStudents.get(i);
    		if (temp.getDorm() != null) {
    			allStudents.remove(i);
    			i--;
    		}
    	}
    	return allStudents;
    }
    
    
    // creates one
    public Student createStudent(Student s) {
        return studentRepository.save(s);
    }

    // retrieves one by id
    public Student findStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            return null;
        }
    }
    
    // updates one
    public Student updateStudent(Student s) {
       	return studentRepository.save(s);
    }
    
    // deletes one
    public void deleteStudent(Long id) {
    	studentRepository.deleteById(id);
    }
}