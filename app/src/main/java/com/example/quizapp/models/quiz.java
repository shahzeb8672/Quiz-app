package com.example.quizapp.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class quiz {


    public String id;
    public String title;
    public Map<String, questions> questions = Map.of();

    public quiz() {
    }

    public quiz(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public Map<String, questions> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, questions> questions) {
        this.questions = questions;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}