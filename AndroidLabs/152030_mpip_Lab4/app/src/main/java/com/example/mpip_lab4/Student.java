package com.example.mpip_lab4;

public class Student {

    Integer index;
    String name;
    String surname;
    Integer telephone;
    String adress;


    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Student(Integer index, String name, String surname, Integer telephone, String adress) {
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.adress = adress;
    }


    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public String getAdress() {
        return adress;
    }


}
