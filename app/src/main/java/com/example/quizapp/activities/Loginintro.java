package com.example.quizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

;import static com.example.quizapp.activities.R.*;

public class Loginintro extends AppCompatActivity {
    private Button start;
    private FirebaseAuth Authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginintro);
        start=(Button) findViewById(R.id.btnGetStarted);
        Authentication= FirebaseAuth.getInstance();
        if (Authentication.getCurrentUser() != null) {

            Toast.makeText(Loginintro.this, "User already Logged in",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Loginintro.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
        else{
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Loginintro.this, LoginActivity.class));
                    finish();
                }
            });
        }


    }
}
