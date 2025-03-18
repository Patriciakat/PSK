package com.example.psk.service;

import com.example.psk.DAO.DegreeRepository;
import com.example.psk.entities.Degree;
import com.example.psk.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DegreeService {
    @Inject
    private DegreeRepository degreeRepository;

    @Transactional
    public void createDegree(Degree degree) {
        if (degree.getTitle() == null || degree.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Degree title cannot be empty");
        }

        Degree existingDegree = degreeRepository.findByTitle(degree.getTitle());
        if (existingDegree != null) {
            throw new IllegalArgumentException("A degree with this title already exists");
        }

        degreeRepository.save(degree);
    }

    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    public Degree getDegreeById(Long id) {
        return degreeRepository.findById(id);
    }

    @Transactional
    public Degree updateDegree(Degree degree) {
        Degree existingDegree = degreeRepository.findById(degree.getId());
        if (existingDegree == null) {
            throw new IllegalArgumentException("Degree with ID " + degree.getId() + " not found");
        }
        if (degree.getTitle() == null || degree.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Degree title cannot be empty");
        }

        return degreeRepository.update(degree);
    }

    @Transactional
    public void deleteDegree(Long id) {
        // are there any students enrolled in this degree
        Long studentCount = degreeRepository.countStudents(id);
        if (studentCount > 0) {
            throw new IllegalStateException("Cannot delete a degree with enrolled students");
        }

        degreeRepository.delete(id);
    }

    @Transactional
    public void addStudentToDegree(Long degreeId, Student student) {
        Degree degree = degreeRepository.findById(degreeId);
        if (degree == null) {
            throw new IllegalArgumentException("Degree not found");
        }

        degree.addStudent(student);
        degreeRepository.update(degree);
    }
}
