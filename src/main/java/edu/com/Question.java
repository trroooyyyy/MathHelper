package edu.com;

public class Question {
    private int id;
    private String equation;
    private double x;

    public Question() {
    }

    public Question(int id, String equation, double x) {
        this.id = id;
        this.equation = equation;
        this.x = x;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", equation='" + equation + '\'' +
                ", x=" + x +
                '}';
    }
}

