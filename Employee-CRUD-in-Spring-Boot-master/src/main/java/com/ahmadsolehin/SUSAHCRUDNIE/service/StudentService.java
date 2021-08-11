package com.ahmadsolehin.SUSAHCRUDNIE.service;
import java.util.List;
import com.ahmadsolehin.SUSAHCRUDNIE.model.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Iterable<Student> findAll();

    List<Student> search(String q);

    Student findByStudentId(Integer id);

    void save(Student contact);

    void deleteById(int id);
}