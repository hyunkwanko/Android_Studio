package com.example.hyeongwan.code;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.button);
        final EditText content = (EditText)findViewById(R.id.edit);
        final TextView result = (TextView)findViewById(R.id.textview);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == button){
                    result.setText(content.getText().toString().trim());
                }
            }
        });
    }
}

