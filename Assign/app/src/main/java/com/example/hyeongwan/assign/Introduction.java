package com.example.hyeongwan.assign;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Introduction extends AppCompatActivity {
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_page);

        final Intent homeScreen =  new Intent(this, HomeScreen.class);
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeScreen);
            }
        });
    }
}
