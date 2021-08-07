package com.example.quizapp.utils;

import com.example.quizapp.activities.R;

import java.util.Random;

 public class icon_picker {
    int array[] = {R.drawable.ic_books_svgrepo_com,
            R.drawable.ic_books_svgrepo_com,
            R.drawable.ic_books_hand_drawn_educational_tools_svgrepo_com,
            R.drawable.ic_notes_svgrepo_com,
            R.drawable.ic_education_svgrepo_com,
            R.drawable.ic_messages_mails_svgrepo_com,
            R.drawable.ic_pencil_svgrepo_com,
            R.drawable.ic_notes_svgrepo_com};
    int current_icon = 0;

    public int get_icon(){
        Random rand = new Random();
        current_icon = rand.nextInt(array.length-1);

        return array[current_icon];
    }
}
