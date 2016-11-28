package com.iesb.sergio.iesbapp.model;


public class Grade {

    private String discipline;
    private Integer frequence;
    private Double gradeP1;
    private Double gradeP2;
    private Double gradeP3;

    public Grade() {
    }

    public Grade(String discipline, Integer frequence, Double gradeP1, Double gradeP2, Double gradeP3) {
        this.discipline = discipline;
        this.frequence = frequence;
        this.gradeP1 = gradeP1;
        this.gradeP2 = gradeP2;
        this.gradeP3 = gradeP3;
    }

    public Integer getFrequence() {
        return frequence;
    }

    public void setFrequence(Integer frequence) {
        this.frequence = frequence;
    }

    public Double getGradeP1() {
        return gradeP1;
    }

    public void setGradeP1(Double gradeP1) {
        this.gradeP1 = gradeP1;
    }

    public Double getGradeP2() {
        return gradeP2;
    }

    public void setGradeP2(Double gradeP2) {
        this.gradeP2 = gradeP2;
    }

    public Double getGradeP3() {
        return gradeP3;
    }

    public void setGradeP3(Double gradeP3) {
        this.gradeP3 = gradeP3;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
