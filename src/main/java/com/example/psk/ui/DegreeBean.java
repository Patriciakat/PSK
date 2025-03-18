package com.example.psk.ui;

import com.example.psk.entities.Degree;
import com.example.psk.service.DegreeService;
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
public class DegreeBean implements Serializable {

    @Inject
    private DegreeService degreeService;

    private List<Degree> degrees;
    private List<SelectItem> degreesForSelect;
    private Degree newDegree = new Degree();

    @PostConstruct
    public void init() {
        degrees = degreeService.getAllDegrees();
        degreesForSelect = new ArrayList<>();

        for (Degree degree : degrees) {
            degreesForSelect.add(new SelectItem(degree.getId(), degree.getTitle()));
        }
    }

    public String createDegree() {
        degreeService.createDegree(newDegree);
        return "degrees?faces-redirect=true";
    }

    public String deleteDegree(Long id) {
        degreeService.deleteDegree(id);
        return "degrees?faces-redirect=true";
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public List<SelectItem> getDegreesForSelect() {
        return degreesForSelect;
    }

    public Degree getNewDegree() {
        return newDegree;
    }

    public void setNewDegree(Degree newDegree) {
        this.newDegree = newDegree;
    }
}