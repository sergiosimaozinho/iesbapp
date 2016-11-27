package com.iesb.sergio.iesbapp.model;


import java.util.ArrayList;

public class UserDiscipline {

    private String emailUsuario;
    private ArrayList<Discipline> disciplines;

    public UserDiscipline() {
    }

    public UserDiscipline(String emailUsuario, ArrayList<Discipline> disciplines) {
        this.emailUsuario = emailUsuario;
        this.disciplines = disciplines;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
