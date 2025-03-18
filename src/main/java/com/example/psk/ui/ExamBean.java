package com.example.psk.ui;

import com.example.psk.entities.Exam;
import com.example.psk.service.ExamService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.model.SelectItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ExamBean implements Serializable {

    @Inject
    private ExamService examService;

    private List<Exam> exams;
    private List<SelectItem> examsForSelect;
    private Exam newExam = new Exam();

    @PostConstruct
    public void init() {
        exams = examService.getAllExams();
        examsForSelect = new ArrayList<>();

        for (Exam exam : exams) {
            examsForSelect.add(new SelectItem(exam.getId(), exam.getTitle()));
        }
    }

    public String createExam() {
        examService.createExam(newExam);
        return "exams?faces-redirect=true";
    }

    public String deleteExam(Long id) {
        examService.deleteExam(id);
        return "exams?faces-redirect=true";
    }

    public List<Exam> getExams() {
        return exams;
    }


    public Exam getNewExam() {
        return newExam;
    }

    public void setNewExam(Exam newExam) {
        this.newExam = newExam;
    }
}