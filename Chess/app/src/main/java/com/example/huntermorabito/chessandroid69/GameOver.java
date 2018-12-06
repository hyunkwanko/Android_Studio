package com.example.huntermorabito.chessandroid69;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    TextView winnerText;
    Button homeB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        final Intent homeScreen =  new Intent(this, HomeScreen.class);

        Intent mainGame = getIntent();
        final String winner = mainGame.getStringExtra(MainGame.EXTRA_WINNER);

        winnerText = (TextView)findViewById(R.id.winnerText);
        winnerText.setText(winner);

        homeB = (Button)findViewById(R.id.homeB);
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeScreen);
            }
        });
    }
}
