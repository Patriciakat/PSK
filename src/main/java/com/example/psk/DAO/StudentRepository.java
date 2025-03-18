package com.example.psk.DAO;

import com.example.psk.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import java.util.List;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StudentRepository {
    @PersistenceContext(unitName = "PSKPersistence")
    private EntityManager em;

    @Transactional
    public void save(Student student) {
        em.persist(student);
    }
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }
    public Student update(Student student) {
        return em.merge(student);
    }

    public void delete(Long id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }
}