package com.example.hyeongwan.profile_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button buttonadd, buttondel, buttoncancel;
    EditText name, tel, email, dept;
    int i;

    public class Person{
        String name = null, tel, email, dept;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Person[] ppv = new Person[5]; // 5명
        buttonadd = (Button)findViewById(R.id.buttonadd); // Store
        buttondel = (Button)findViewById(R.id.buttondel); // Delete
        buttoncancel = (Button)findViewById(R.id.buttoncancel); // Cancel

        name = (EditText)findViewById(R.id.name);
        tel = (EditText)findViewById(R.id.tel);
        email = (EditText)findViewById(R.id.email);
        dept = (EditText)findViewById(R.id.dept);

        OnClickListener Profile = new OnClickListener(){

            public void onClick(View view) {
                if(view == buttonadd) { // Store
                    for(i = 0; i < 5; i++){
                        if (ppv[i].name.equals(null)){
                            ppv[i].name = name.getText().toString();
                            ppv[i].tel = tel.getText().toString();
                            ppv[i].email = email.getText().toString();
                            ppv[i].dept = dept.getText().toString();
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (i == 4)
                            Toast.makeText(getApplicationContext(), "Full", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(view == buttondel){ // Delete
                }
                else if(view == buttoncancel){ // Cancel
                }
            }
        };

        buttonadd.setOnClickListener(Profile);
        buttondel.setOnClickListener(Profile);
        buttoncancel.setOnClickListener(Profile);
    }
}

