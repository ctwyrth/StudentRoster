package com.ctwyrth.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ctwyrth.studentroster.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    // this method retrieves all from the database
    List<Student> findAll();

}