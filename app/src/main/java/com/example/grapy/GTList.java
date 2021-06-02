package com.example.grapy;

public class GTList {
    private String name;
    private String r_personality;
    private int num_member;

    public GTList(String name, String r_personality, int num_member) {
        this.name = name;
        this.r_personality = r_personality;
        this.num_member = num_member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getR_personality() {
        return r_personality;
    }

    public void setR_personality(String r_personality) {
        this.r_personality = r_personality;
    }

    public int getNum_member() {
        return num_member;
    }

    public void setNum_member(int num_member) {
        this.num_member = num_member;
    }
}
