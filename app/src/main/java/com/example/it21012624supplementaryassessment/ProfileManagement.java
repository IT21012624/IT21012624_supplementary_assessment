package com.example.it21012624supplementaryassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it21012624supplementaryassessment.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username, email, password;
    RadioButton male, female;
    Button add, updateProfile;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etUsernamePM);
        email = findViewById(R.id.etEmailPM);
        password = findViewById(R.id.etPasswordPM);
        male = findViewById(R.id.radioButton5);
        female = findViewById(R.id.radioButton6);
        add = findViewById(R.id.btnAddPM);
        updateProfile = findViewById(R.id.btnUpdateProfilePM);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (male.isChecked()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(username.getText().toString(),email.getText().toString(), password.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "User Added. User ID: "+ newID, Toast.LENGTH_SHORT).show();

                Intent i = new  Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

    }

}