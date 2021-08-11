package com.ahmadsolehin.SUSAHCRUDNIE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmadsolehin.SUSAHCRUDNIE.model.Student;
import com.ahmadsolehin.SUSAHCRUDNIE.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository employeeRepository;

    @Override
    public Iterable<Student> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Student> search(String q) {
        return employeeRepository.findByNameContaining(q);
    }
    @Override
    public Student findByStudentId(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        student.setFirstMidMarks(student.getQone()+student.getQtwo()+student.getQthree());
        student.setSecondMidMarks(student.getQfour()+student.getQfive()+student.getQsix());
        employeeRepository.save(student);
    }
    @Override
    public void deleteById(int id) {
        employeeRepository.delete(id);
    }
}