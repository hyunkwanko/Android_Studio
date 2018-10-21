package com.example.hyeongwan.code;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;
import android.widget.RadioGroup;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ToggleButton toggle = (ToggleButton) this.findViewById(R.id.toggleButton);
        final TextView textview= (TextView)findViewById(R.id.textView);
        final RadioButton radio0 = (RadioButton)findViewById(R.id.radio0);
        final RadioButton radio1 = (RadioButton)findViewById(R.id.radio1);
        final RadioButton radio2 = (RadioButton)findViewById(R.id.radio2);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        final TableLayout table = (TableLayout)findViewById(R.id.table);
        final Button buttonFirst = (Button)findViewById(R.id.buttonFirst);

        final ImageView image = (ImageView)findViewById(R.id.imageView);
        final Button buttonEnd = (Button)findViewById(R.id.buttonEnd);

        // 설문지 On/Off
        toggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle.isChecked()){
                    table.setVisibility(View.VISIBLE);
                }else{
                    table.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 재시작
        buttonFirst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle.setChecked(true);
                radio0.setChecked(true);
                recreate();
            }
        });

        // Radio 버튼 1
        radio0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio0.isChecked()){
                    image.setImageResource(R.drawable.angrybird_red);
                }
            }
        });

        // Radio 버튼 2
        radio1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio1.isChecked()){
                    image.setImageResource(R.drawable.angrybird_yellow);
                }
            }
        });

        // Radio 버튼 3
        radio2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio2.isChecked()){
                    image.setImageResource(R.drawable.angrybird_red);
                }
            }
        });

        // 종료 버튼
        buttonEnd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
