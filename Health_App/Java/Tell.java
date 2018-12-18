package com.example.hyunkwan.Assignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class Tell extends AppCompatActivity {

    TextView tellnum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell);

        tellnum = (TextView) findViewById(R.id.tellnum);
        Button readButton = (Button) findViewById(R.id.readBtn);
        readButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    InputStream fis = getResources().openRawResource(R.raw.tell);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    tellnum.setText(new String(buffer));
                    fis.close();
                } catch (IOException e) {
                }
            }
        });

        Button backBtn = (Button)findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
