package com.example.quizapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.adapter.option_adapter;
import com.example.quizapp.models.questions;
import com.example.quizapp.models.quiz;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class questions_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    Button btn_next;
    Button btn_prev;
    Button btn_submit;
    int index = 1;
    public ArrayList<quiz> quizzes = new ArrayList<>();
    public Map<String, questions> questions= Map.of();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        set_up_firebase();
        setUpEventListener();
    }
    public  void bindViews(){
        btn_prev = (Button) findViewById(R.id.btnPrevious);
        btn_next = (Button) findViewById(R.id.btnNext);
        btn_submit = (Button) findViewById(R.id.btnSubmit);
        btn_prev.setVisibility(View.GONE);
        btn_next.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);

        if (index == 1){
            btn_next.setVisibility(View.VISIBLE);
        }
        else if(index == questions.size() || questions.size() == 1){
            btn_submit.setVisibility(View.VISIBLE);
            btn_prev.setVisibility(View.VISIBLE);
        }
        else if(index == 2){
            btn_next.setVisibility(View.VISIBLE);
            btn_prev.setVisibility(View.VISIBLE);
        }


     //   questions question_db = questions.get("questions" + index);
        questions question_db = questions.get("questions" + index);

        Log.i("Check", "bindViews: " + question_db);

        TextView description_of_question = findViewById(R.id.description);
        description_of_question.setText(question_db.description);
        recyclerView = findViewById(R.id.optionList);
        option_adapter option_adaptor = new option_adapter(this, question_db);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(option_adaptor);


    }

    public void set_up_firebase(){
        firestore = FirebaseFirestore.getInstance();
        String quiz_name =  getIntent().getStringExtra("Name");

        firestore.collection("Quizzes")
                .whereEqualTo("title", quiz_name)
                .get()
                .addOnSuccessListener(snapshots -> {
                    if(snapshots!=null && !snapshots.isEmpty()){
                        quizzes.addAll(snapshots.toObjects(quiz.class));
                        questions = quizzes.get(0).questions;
                        bindViews();
                    }
                    else{
                        Toast.makeText(questions_activity.this,"No Quiz found",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(questions_activity.this, MainActivity.class);
                        questions_activity.this.startActivity(intent);
                        finish();
                    }

                });

    }

    public void setUpEventListener(){

        Button btnNext;
        Button btnPrev;
        Button btnFinish;

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrevious);
        btnFinish = findViewById(R.id.btnSubmit);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index -1 ;
                bindViews();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("QUESTIONS", "onClick: " + questions);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json=gson.toJson(quizzes);
                Intent intent = new Intent(questions_activity.this, questions_activity.class);
                intent.putExtra("Quiz", json);
                questions_activity.this.startActivity(intent);
                finish();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index + 1 ;
                bindViews();
            }
        });



    }

}