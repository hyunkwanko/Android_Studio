package com.example.hyunkwan.Assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bmi extends AppCompatActivity {
    Button back, bmi;
    TextView view;
    String a, b;
    EditText e_weight, e_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        e_weight = (EditText) findViewById(R.id.e_weight);
        e_height = (EditText) findViewById(R.id.e_height);

        bmi = (Button)findViewById(R.id.bmi);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = e_weight.getText().toString();
                b = e_height.getText().toString();

                double x = Double.parseDouble(a); // 몸무게
                double y = Double.parseDouble(b); // 키

                double result = x / ((y / 100) * (y / 100));
                view.setText((int) result);
            }
        });

        final Intent homeScreen =  new Intent(this, HomeScreen.class);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeScreen);
            }
        });
    }
}
