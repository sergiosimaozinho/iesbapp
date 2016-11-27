package com.iesb.sergio.iesbapp.model;

/**
 * Created by henriquesantos on 27/11/16.
 */
public class Discipline {
    private String name;
    private String teacherName;
    private String teacherEmail;

    public Discipline() {
    }

    public Discipline(String name, String teacherName, String teacherEmail) {
        this.name = name;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}
