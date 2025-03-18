package com.example.psk.DAO;

import com.example.psk.entities.Exam;
import com.example.psk.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ExamRepository {
    @PersistenceContext(unitName = "PSKPersistence")
    private EntityManager em;

    public void save(Exam exam) {
        em.persist(exam);
    }
    public Exam findById(Long id) {
        return em.find(Exam.class, id);
    }

    public List<Exam> findAll() {
        return em.createQuery("SELECT e FROM Exam e", Exam.class).getResultList();
    }
    public Exam update(Exam exam) {
        return em.merge(exam);
    }

    public void delete(Long id) {
        Exam exam = em.find(Exam.class, id);
        if (exam != null) {
            em.remove(exam);
        }
    }

    // Remove student from exam
    public void removeStudent(Long examId, Student studentId) {
        Exam exam = em.find(Exam.class, examId);
        Student student = em.find(Student.class, studentId);
        if (exam != null && student != null) {
            exam.getStudents().remove(student);
            student.getExams().remove(exam);
            em.merge(exam);
            em.merge(student);
        }
    }
}
