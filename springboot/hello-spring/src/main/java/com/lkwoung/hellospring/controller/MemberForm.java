package com.lkwoung.hellospring.controller;

public class MemberForm {
    private String name; // createMemberForm.html로 부터 name값이 들어옴

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
