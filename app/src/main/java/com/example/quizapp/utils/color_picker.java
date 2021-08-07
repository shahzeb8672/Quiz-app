package com.example.quizapp.utils;

import java.util.Random;

public class color_picker {
    String array[] = {"#ff0006", "#ff008d", "#ff008d", "#00c3ff", "#33cc5a", "#ffdab9", "#5f9ea0", "#7fff00", "#cd5c5c", "#ff69b4"};
    int current_color = 0;

    public String get_color(){
        Random rand = new Random();
        current_color = rand.nextInt(array.length-1);
        return array[current_color];
    }
}