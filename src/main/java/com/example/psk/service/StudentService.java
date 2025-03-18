package com.example.psk.service;

import com.example.psk.DAO.ExamRepository;
import com.example.psk.DAO.StudentRepository;
import com.example.psk.entities.Exam;
import com.example.psk.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentService {
    @Inject
    private StudentRepository studentRepository;

    @Inject
    private ExamRepository examRepository;

    @Transactional
    public void registerStudent(Student student) {
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (student.getCode() == null) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        if (student.getDegree() == null) {
            throw new IllegalArgumentException("Student must be assigned to a degree");
        }

        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void deleteStudent(Long id) {
            studentRepository.delete(id);
    }

    @Transactional
    public void enrollInExam(Long studentId, Exam exam) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        student.addExam(exam);
        studentRepository.update(student);
    }

    @Transactional
    public void withdrawFromExam(Long studentId, Exam exam) {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        student.removeExam(exam);
        studentRepository.update(student);
    }
}