package com.ctwyrth.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctwyrth.studentroster.models.Dorm;
import com.ctwyrth.studentroster.repositories.DormRepository;

@Service
public class DormService {
    @Autowired
    private DormRepository dormRepository;
    
    // shows all
    public List<Dorm> allDorms() {
        return dormRepository.findAll();
    }
    
    // creates one
    public Dorm createDorm(Dorm d) {
        return dormRepository.save(d);
    }

    // retrieves one by id
    public Dorm findDorm(Long id) {
        Optional<Dorm> optionalDorm = dormRepository.findById(id);
        if(optionalDorm.isPresent()) {
        	optionalDorm.get().displayDorm();
            return optionalDorm.get();
        } else {
            return null;
        }
    }
    
    // updates one
    public Dorm updateDorm(Dorm d) {
    	d.displayDorm();
    	System.out.println("From Service: " + d.getStudents());
       	return dormRepository.save(d);
    }
    
    // deletes one
    public void deleteDorm(Long id) {
    	dormRepository.deleteById(id);
    }
}