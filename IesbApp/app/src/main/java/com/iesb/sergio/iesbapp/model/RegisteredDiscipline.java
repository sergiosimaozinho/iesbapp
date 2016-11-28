package com.iesb.sergio.iesbapp.model;


public class RegisteredDiscipline {
    private String name;
    private String Day;
    private String room;

    public RegisteredDiscipline(String name, String day, String room) {
        this.name = name;
        Day = day;
        this.room = room;
    }

    public RegisteredDiscipline() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
