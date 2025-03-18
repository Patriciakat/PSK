package com.example.psk.service;

import com.example.psk.DAO.ExamRepository;
import com.example.psk.entities.Exam;
import com.example.psk.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ExamService {
    @Inject
    private ExamRepository examRepository;

    @Transactional
    public void createExam(Exam exam) {
        if (exam.getTitle() == null || exam.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Exam title cannot be empty");
        }

        examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id);
    }

    @Transactional
    public void deleteExam(Long id) {
        examRepository.delete(id);
    }

}
