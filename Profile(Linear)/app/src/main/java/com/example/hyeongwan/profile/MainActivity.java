package com.example.hyeongwan.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFriend(View target){
        Toast.makeText(getApplicationContext(), "버튼 클릭되었다.", Toast.LENGTH_SHORT).show();
    }
}
