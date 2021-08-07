package com.example.quizapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.adapter.quiz_adapter;
import com.example.quizapp.models.quiz;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle actionBar;
    DrawerLayout d_Layout;
    NavigationView navigationView;
    Toolbar tool_bar;
    ActionBarDrawerToggle toggle;
    FirebaseFirestore firestore;

    ArrayList<quiz> quiz_list = new ArrayList<quiz>();
    quiz_adapter adapter = new quiz_adapter(this, quiz_list);

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_up_views();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void set_up_views() {
        set_up_drawer_layout();
        set_up_recycle_view();
        set_up_fireStore();

    }
    public  void set_up_drawer_layout(){
        navigationView = findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            this.startActivity(intent);
            finish();
            return true;
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void set_up_fireStore(){
        firestore = FirebaseFirestore.getInstance();
        CollectionReference quiz = firestore.collection("quizzes");
        Map<String, Object> data1 = new HashMap<>();
        quiz.addSnapshotListener((value, error) ->{
            if(value == null || error!=null){
                Toast.makeText(getApplicationContext(),"Error fetching the Data", Toast.LENGTH_SHORT).show();
            }
            quiz object = new quiz();
            Log.d(TAG, "Current data: " + value.toObjects(quiz.class).toString());
            quiz_list.clear();
            quiz_list.addAll(value.toObjects(quiz.class));
            adapter.notifyDataSetChanged();

        } );
    }

    public void set_up_recycle_view(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recycle_view = findViewById(R.id.quiz_recycle_view);
        recycle_view.setAdapter(adapter);
        recycle_view.setLayoutManager(gridLayoutManager);
    }

}