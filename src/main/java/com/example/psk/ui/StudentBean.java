package com.example.psk.ui;

import com.example.psk.entities.Degree;
import com.example.psk.entities.Student;
import com.example.psk.entities.Exam;
import com.example.psk.service.DegreeService;
import com.example.psk.service.ExamService;
import com.example.psk.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class StudentBean implements Serializable {
    @Inject
    private StudentService studentService;

    @Inject
    private DegreeService degreeService;

    @Inject
    private ExamService examService;

    private List<Student> students;
    private Student newStudent = new Student();
    private Long selectedDegreeId;

    // For student details
    private Long selectedStudentId;
    private Student selectedStudent;
    private Long selectedExamId;
    private List<Exam> availableExams;

    @PostConstruct
    public void init() {
        students = studentService.getAllStudents();
    }

    public void loadStudents() {
        students = studentService.getAllStudents();
    }
    public String createStudent() {
        try {
            Degree degree = degreeService.getDegreeById(selectedDegreeId);
            newStudent.setDegree(degree);

            // Convert String to Integer if necessary
            if (newStudent.getCode() == null && FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("addStudentForm:studentId") != null) {
                try {
                    String codeStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("addStudentForm:studentId");
                    newStudent.setCode(Integer.parseInt(codeStr));
                } catch (NumberFormatException e) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Student code must be a number"));
                    return null;
                }
            }

            studentService.registerStudent(newStudent);

            // Reset form
            newStudent = new Student();
            selectedDegreeId = null;

            // Refresh student list
            loadStudents();

            return "students?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public String deleteStudent(Long id) {
        studentService.deleteStudent(id);
        return "students?faces-redirect=true";
    }

    // Load the selected student and available exams
    public void loadSelectedStudent() {
        if (selectedStudentId != null) {
            selectedStudent = studentService.getStudentById(selectedStudentId);
            if (selectedStudent != null) {
                refreshAvailableExams();
            }
        }
    }

    // Select a student to display details
    public String selectStudent(Long id) {
        selectedStudentId = id;
        loadSelectedStudent();
        return "students?faces-redirect=true&selectedStudentId=" + id;
    }

    private void refreshAvailableExams() {
        if (selectedStudent != null) {
            // Get all exams
            List<Exam> allExams = examService.getAllExams();

            // Filter out exams the student is already enrolled in
            List<Long> enrolledExamIds = selectedStudent.getExams().stream()
                    .map(Exam::getId)
                    .collect(Collectors.toList());

            availableExams = allExams.stream()
                    .filter(exam -> !enrolledExamIds.contains(exam.getId()))
                    .collect(Collectors.toList());
        }
    }

    public String enrollInExam() {
        if (selectedStudentId != null && selectedExamId != null) {
            try {
                Exam exam = examService.getExamById(selectedExamId);
                studentService.enrollInExam(selectedStudentId, exam);

                // Refresh student details
                loadSelectedStudent();
                selectedExamId = null;

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Student enrolled in exam"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
        }
        return "students?faces-redirect=true&selectedStudentId=" + selectedStudentId;
    }

    public String withdrawFromExam(Long examId) {
        if (selectedStudentId != null) {
            try {
                Exam exam = examService.getExamById(examId);
                studentService.withdrawFromExam(selectedStudentId, exam);

                // Refresh student details
                loadSelectedStudent();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Student withdrawn from exam"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
        }
        return "students?faces-redirect=true&selectedStudentId=" + selectedStudentId;
    }

    // Getters and setters
    public List<Student> getStudents() {
        return students;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public Long getSelectedDegreeId() {
        return selectedDegreeId;
    }

    public void setSelectedDegreeId(Long selectedDegreeId) {
        this.selectedDegreeId = selectedDegreeId;
    }

    public Long getSelectedStudentId() {
        return selectedStudentId;
    }

    public void setSelectedStudentId(Long selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
        if (selectedStudentId != null) {
            loadSelectedStudent();
        } else {
            selectedStudent = null;
        }
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public Long getSelectedExamId() {
        return selectedExamId;
    }

    public void setSelectedExamId(Long selectedExamId) {
        this.selectedExamId = selectedExamId;
    }

    public List<Exam> getAvailableExams() {
        return availableExams;
    }
}
