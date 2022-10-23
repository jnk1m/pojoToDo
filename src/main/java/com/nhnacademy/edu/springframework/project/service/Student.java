package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;

public class Student {
    private final int seq; //학번
    private final String name;
    private Score score;

    public Student(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSeq() {
        return seq;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore(){
        return this.score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}'+ '\n';
    }
}
