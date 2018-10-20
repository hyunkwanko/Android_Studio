package com.example.hyeongwan.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    Button buttonadd,buttondel,buttonmul,buttonsub,buttonresult,buttonclear,buttondot;

    EditText result;

    double a;
    int ADD = 1;
    int DEL = 2;
    int MUL = 3;
    int SUB = 4;
    int TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        button0 = (Button)findViewById(R.id.button0);
        buttondot = (Button)findViewById(R.id.buttondot);
        buttonadd = (Button)findViewById(R.id.buttonadd);
        buttondel = (Button)findViewById(R.id.buttondel);
        buttonmul = (Button)findViewById(R.id.buttonmul);
        buttonsub = (Button)findViewById(R.id.buttonsub);
        buttonresult = (Button)findViewById(R.id.buttonresult);
        buttonclear = (Button)findViewById(R.id.buttonclear);

        result = (EditText)findViewById(R.id.result_view); // Result

        OnClickListener Calculator = new OnClickListener(){
            public void onClick(View view) {
                if(view == button1){ // 1
                    result.setText(result.getText().toString() + 1);
                }
                else if(view == button2){ // 2
                    result.setText(result.getText().toString() + 2);
                }
                else if(view == button3){ // 3
                    result.setText(result.getText().toString() + 3);
                }
                else if(view == button4){ // 4
                    result.setText(result.getText().toString() + 4);
                }
                else if(view == button5){ // 5
                    result.setText(result.getText().toString() + 5);
                }
                else if(view == button6){ // 6
                    result.setText(result.getText().toString() + 6);
                }
                else if(view == button7){ // 7
                    result.setText(result.getText().toString() + 7);
                }
                else if(view == button8){ // 8
                    result.setText(result.getText().toString() + 8);
                }
                else if(view == button9){ // 9
                    result.setText(result.getText().toString() + 9);
                }
                else if(view == button0){ // 0
                    result.setText(result.getText().toString() + 0);
                }
                else if(view == buttondot){
                    result.setText(result.getText().toString() + ".");
                }
                else if(view == buttonadd){ // Add
                    a = Double.parseDouble(result.getText().toString().trim());
                    result.setText("0");
                    TYPE = 1;
                }
                else if(view == buttondel){ // Del
                    a = Double.parseDouble(result.getText().toString().trim());
                    result.setText("0");
                    TYPE = 2;
                }
                else if(view == buttonmul){ // Mul
                    a = Double.parseDouble(result.getText().toString().trim());
                    result.setText("0");
                    TYPE = 3;
                }
                else if(view == buttonsub){ //Sub
                    a = Double.parseDouble(result.getText().toString().trim());
                    result.setText("0");
                    TYPE = 4;
                }
                else if(view == buttonresult){ // Result
                    if(TYPE == ADD){ // Cal.Add
                        a = a + Double.parseDouble(result.getText().toString().trim());
                        result.setText("" + a);
                    }
                    else if(TYPE == DEL){ // Cal.Del
                        a = a - Double.parseDouble(result.getText().toString().trim());
                        result.setText("" + a);
                    }
                    else if(TYPE == MUL){ // Cal.Mul
                        a = a * Double.parseDouble(result.getText().toString().trim());
                        result.setText("" + a);
                    }
                    else if(TYPE == SUB){ // Cal.Sub
                        a = a / Double.parseDouble(result.getText().toString().trim());
                        result.setText("" + a);
                    }
                }
                else if(view == buttonclear){ // clear
                    result.setText("");
                }
            }
        };

        button1.setOnClickListener(Calculator);
        button2.setOnClickListener(Calculator);
        button3.setOnClickListener(Calculator);
        button4.setOnClickListener(Calculator);
        button5.setOnClickListener(Calculator);
        button6.setOnClickListener(Calculator);
        button7.setOnClickListener(Calculator);
        button8.setOnClickListener(Calculator);
        button9.setOnClickListener(Calculator);
        button0.setOnClickListener(Calculator);
        buttondot.setOnClickListener(Calculator);
        buttonadd.setOnClickListener(Calculator);
        buttondel.setOnClickListener(Calculator);
        buttonmul.setOnClickListener(Calculator);
        buttonsub.setOnClickListener(Calculator);
        buttonresult.setOnClickListener(Calculator);
        buttonclear.setOnClickListener(Calculator);
    }
}

