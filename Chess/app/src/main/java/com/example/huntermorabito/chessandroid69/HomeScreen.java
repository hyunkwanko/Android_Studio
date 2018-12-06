package com.example.huntermorabito.chessandroid69;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import chess.*;

public class HomeScreen extends AppCompatActivity {
    public static final String EXTRA_RECORDED_GAMES = "EXTRA_RECORDED_GAMES";

    public static ArrayList<RecordedGame> recordedGames;

    Button playB, recordedGamesB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final Intent playGame = new Intent(this, MainGame.class);
        final Intent choosePlayback = new Intent(this, ChoosePlayback.class);

        if(recordedGames == null) {
            recordedGames = deserializeGames();
        }

        try {
            Intent gameOver = getIntent();
            RecordedGame newRecordedGame = (RecordedGame) gameOver.getSerializableExtra(GameOver.EXTRA_RECORDED);
            if (newRecordedGame != null) {
                Log.d("Pass", "gameadded");
                //add game to saved serialized games
                recordedGames.add(newRecordedGame);
                serializeGames(recordedGames);
            }
        } catch (Exception e) {
            Log.d("Err", "NOTCAUGHT");
        }

        if (!recordedGames.isEmpty()) {
            for (RecordedGame game : recordedGames) {
                Log.d("RecordedGameTime", game.getName());
            }
        }

        playB = (Button) findViewById(R.id.playB);
        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(playGame);
            }
        });
        recordedGamesB = (Button) findViewById(R.id.recordedGamesB);
        recordedGamesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {choosePlayback.removeExtra(EXTRA_RECORDED_GAMES);
                }catch(Exception e){}
                choosePlayback.putExtra(EXTRA_RECORDED_GAMES, recordedGames);
                startActivity(choosePlayback);
            }
        });
    }

    public void serializeGames(ArrayList<RecordedGame> games) {
        try {
            File newFile = new File(getFilesDir().getPath() + "/recordings.ser");
            FileOutputStream fileOut = new FileOutputStream(newFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.flush();
            out.writeObject(games);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecordedGame> deserializeGames() {
        ArrayList<RecordedGame> newSavedGames = new ArrayList<RecordedGame>();
        try {
            File newFile = new File(getFilesDir().getPath() + "/recordings.ser");
            FileInputStream fileIn = new FileInputStream(newFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newSavedGames = (ArrayList<RecordedGame>) in.readObject();
            //Log.d("Serializer", RecordedGame.get(0).getName());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return newSavedGames;
    }
    public void clearSer() {
        serializeGames(new ArrayList<RecordedGame>());
    }
}
