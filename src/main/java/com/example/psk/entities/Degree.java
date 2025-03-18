package com.example.psk.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String title;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    // Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Helper methods
    public void addStudent(Student student) {
        students.add(student);
        student.setDegree(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setDegree(null);
    }
}
