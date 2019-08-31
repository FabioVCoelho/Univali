package com.example.lista4e5;

public class Question {
    protected String name;
    protected String question;
    protected Class classe;

    public Question(String name, String question, Class classe) {
        this.name = name;
        this.question = question;
        this.classe = classe;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question + ") " + name;
    }
}
