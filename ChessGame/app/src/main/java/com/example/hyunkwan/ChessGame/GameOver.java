package com.example.hyunkwan.ChessGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    TextView view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        final Intent homeScreen =  new Intent(this, HomeScreen.class);

        Intent mainGame = getIntent();
        final String winner = mainGame.getStringExtra(MainGame.EXTRA_WINNER);

        view = (TextView)findViewById(R.id.view);
        view.setText(winner);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeScreen);
            }
        });
    }
}
