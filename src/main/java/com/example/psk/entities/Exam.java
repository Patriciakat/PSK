package com.example.psk.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String title;

    private Integer score;

    @ManyToMany(mappedBy = "exams", fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    // Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Helper methods
    public void addStudent(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
            if (!student.getExams().contains(this)) {
                student.getExams().add(this);
            }
        }
    }

    public void removeStudent(Student student) {
        if (this.students.contains(student)) {
            this.students.remove(student);
            if (student.getExams().contains(this)) {
                student.getExams().remove(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
