package com.example.it21012624supplementaryassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it21012624supplementaryassessment.Database.DBHandler;

public class MainActivity extends AppCompatActivity {

    EditText email, password, signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                if (dbHandler.loginUser(email.getText().toString(), password.getText().toString())){
                    Toast.makeText(MainActivity.this, "Logged in Scuccessfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),EditProfile.class);
                }
                else {
                    Toast.makeText(MainActivity.this, "No User or Invalid User Details", Toast.LENGTH_SHORT).show();
                    email.setText(null);
                    password.setText(null);
                }
            }
        });

    }
}