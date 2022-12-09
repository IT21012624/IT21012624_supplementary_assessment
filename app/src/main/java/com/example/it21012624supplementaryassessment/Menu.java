package com.example.it21012624supplementaryassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        order = findViewById(R.id.btnorder1);
        order = findViewById(R.id.btnorder2);
        order = findViewById(R.id.btnorder3);
        order = findViewById(R.id.btnorder4);
        order = findViewById(R.id.btnorder5);
        order = findViewById(R.id.btnorder6);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Payment.class);
                startActivity(i);
            }
        });

    }
}