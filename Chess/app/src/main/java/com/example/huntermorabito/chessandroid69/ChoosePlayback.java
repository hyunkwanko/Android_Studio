package com.example.huntermorabito.chessandroid69;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import chess.RecordedGame;


public class ChoosePlayback extends AppCompatActivity {

    public static final String EXTRA_SELECTED_GAME = "EXTRA_SELECTED_GAME";

    ListView listViewGames;
    Button nameSortB, dateSortB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_playback);

        final Intent homeScreen = new Intent(this, HomeScreen.class);
        final Intent runPlayback = new Intent(this, RunPlayback.class);

        Intent gameOver = getIntent();
        final ArrayList<RecordedGame> recordedGames = (ArrayList<RecordedGame>)gameOver.getSerializableExtra(HomeScreen.EXTRA_RECORDED_GAMES);

        listViewGames = (ListView)findViewById(R.id.listViewGames);
        sortByName(recordedGames);
        populateList(recordedGames);

        nameSortB = (Button) findViewById(R.id.nameSortB);
        nameSortB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByName(recordedGames);
                populateList(recordedGames);
            }
        });

        dateSortB = (Button)findViewById(R.id.dateSortB);
        dateSortB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByDate(recordedGames);
                populateList(recordedGames);
            }
        });

        listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                runPlayback.putExtra(EXTRA_SELECTED_GAME, (Serializable)listViewGames.getItemAtPosition(i));
                startActivity(runPlayback);
            }
        });

        if (!recordedGames.isEmpty()) {
            for (RecordedGame game : recordedGames) {
                Log.d("RecordedGameTime", game.getName());
            }
        }else{
            Log.d("Teg","Empty");
        }
    }


    private void populateList(ArrayList<RecordedGame> recordedGames) {
        RecordedGameAdapter adapter = new RecordedGameAdapter(this, R.layout.recorded_item, recordedGames);
        listViewGames.setAdapter(adapter);
    }

    public void sortByName(ArrayList<RecordedGame> recordedGames){
        Collections.sort(recordedGames, new Comparator<RecordedGame>(){
            @Override
            public int compare(RecordedGame g1, RecordedGame g2) {
               return g1.getName().compareToIgnoreCase(g2.getName());
            }
        });
    }

    public void sortByDate(ArrayList<RecordedGame> recordedGames){
        Collections.sort(recordedGames, new Comparator<RecordedGame>(){
            @Override
            public int compare(RecordedGame g1, RecordedGame g2) {
                return g1.getDate().compareTo(g2.getDate());
            }
        });
    }
}
