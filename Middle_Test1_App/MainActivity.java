package com.example.hyeongwan.code1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = (Button)findViewById(R.id.button1);
        final Button button2 = (Button)findViewById(R.id.button2);
        final TextView textview = (TextView)findViewById(R.id.textview);
        final LinearLayout back2 = (LinearLayout)findViewById(R.id.back2);

        textview.setVisibility(View.INVISIBLE);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setVisibility(View.VISIBLE);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText("매사에 게을러지지 말자!");
            }
        });
    }
}