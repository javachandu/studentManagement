package com.ahmadsolehin.SUSAHCRUDNIE.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ahmadsolehin.SUSAHCRUDNIE.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByNameContaining(String q);
    Student findByRollNo(String rollno);
    Student findById(Integer id);

}