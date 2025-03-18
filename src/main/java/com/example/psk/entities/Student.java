package com.example.psk.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Column(name = "student_code")
    private Integer code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "STUDENT_EXAM",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> exams = new ArrayList<>();

    // Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(Integer studentId) {
        this.code = studentId;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void addExam(Exam exam) {
        if (!this.exams.contains(exam)) {
            this.exams.add(exam);
            if (!exam.getStudents().contains(this)) {
                exam.getStudents().add(this);
            }
        }
    }

    public void removeExam(Exam exam) {
        if (this.exams.contains(exam)) {
            this.exams.remove(exam);
            if (exam.getStudents().contains(this)) {
                exam.getStudents().remove(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
