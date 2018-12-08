package com.example.hyunkwan.ChessGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import chess.*;
import pieces.*;

public class MainGame extends AppCompatActivity {

    public final static String EXTRA_WINNER = "EXTRA_WINNER";

    String firstTag;
    String secondTag;
    boolean isWhiteTurn, undoTriggered, drawOffered;

    Board board;
    ArrayList<Move> moves;

    TextView turnText, messageText;
    Button undoB, aiB, drawB, resignB;
    ImageView firstSpace, secondSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        moves = new ArrayList<Move>();

        isWhiteTurn = true;
        undoTriggered = false;
        drawOffered = false;

        board = new Board();
        board.init();
        drawBoard();

        turnText = (TextView) findViewById(R.id.turnText);
        messageText = (TextView) findViewById(R.id.messageText);
        turnText.setText("흰색 차례");
        messageText.setText("");

        undoB = (Button) findViewById(R.id.undoB);
        undoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoClicked();
            }
        });

        aiB = (Button) findViewById(R.id.aiB);
        aiB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiClicked();
            }
        });

        drawB = (Button) findViewById(R.id.drawB);
        drawB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawClicked();
            }
        });
        resignB = (Button) findViewById(R.id.resignB);
        resignB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resignClicked();
            }
        });

        firstTag = "";
        secondTag = "";
    }

    public void spaceClicked(View space) {
        if (firstTag == "") {
            firstTag = space.getTag().toString();
            firstSpace = (ImageView) space;
            messageText.setText("");
        } else {
            secondTag = space.getTag().toString();
            Coordinate start = new Coordinate(firstTag);
            Coordinate end = new Coordinate(secondTag);
            if (!Action.movePiece(isWhiteTurn, board, start, end, "")) {
                messageText.setText("유효하지 않습니다.");
            } else {
                updateMove(start, end);
            }
            firstTag = "";
            secondTag = "";
        }
    }

    public void tryMove(){
        String startTag = firstSpace.getTag().toString();
        String endTag = secondSpace.getTag().toString();

        Coordinate start = new Coordinate(startTag);
        Coordinate end = new Coordinate(endTag);

        if (!Action.movePiece(isWhiteTurn, board, start, end, "")) {
            messageText.setText("유효하지 않습니다.");
        } else {
            updateMove(start, end);
        }

        firstSpace = null;
        secondSpace = null;
    }

    public void undoClicked() {
        if (!undoTriggered) {
            try {
                board.undo();
                drawBoard();
                updateTurn();
                moves.remove(moves.size()-1);
                undoTriggered = true;
            } catch (Exception e) {}
        }
    }

    public void aiClicked() {
        while (true) {
            Random rand = new Random();
            int randRow = rand.nextInt((7 - 0) + 1);
            int randCol = rand.nextInt((7 - 0) + 1);
            Coordinate start = new Coordinate(randRow, randCol);
            randRow = rand.nextInt((7 - 0) + 1);
            randCol = rand.nextInt((7 - 0) + 1);
            Coordinate end = new Coordinate(randRow, randCol);

            if (Action.movePiece(isWhiteTurn, board, start, end, "")) {
                updateMove(start, end);
                return;
            }
        }
    }

    public void drawClicked() {
        if (drawOffered) {
            gameOver("무승부!");
        } else {
            messageText.setText("무승부 요청");
            drawOffered = true;
        }
    }

    public void resignClicked() {
        String winner = "";
        if(isWhiteTurn){
            winner = "검은색 승리";
        }else{
            winner = "흰색 승리";
        }
        gameOver(winner);
    }

    public void gameOver(String winner){
        Intent intent = new Intent(this, Introduce.class);
        intent.putExtra(EXTRA_WINNER, winner);
        startActivity(intent);
    }

    public void updateTurn() {
        isWhiteTurn = !isWhiteTurn;
        if (isWhiteTurn) {
            turnText.setText("흰색 차례");
        } else {
            turnText.setText("검은색 차례");
        }
    }

    public void updateMove(Coordinate start, Coordinate end) {
        if (drawOffered) {
            drawOffered = false;
        }
        board.update(start, end, "");
        drawBoard();
        if (Winner.inCheck(board, isWhiteTurn)) {
            if (Winner.checkmate(board, isWhiteTurn)) {
                messageText.setText("체크메이트");
                if (isWhiteTurn) {
                    gameOver("흰색 승리");
                } else {
                    gameOver("검은색 승리");
                }
            } else {
                messageText.setText("체크");
            }
        }

        updateTurn();
        moves.add(new Move(start, end));
        undoTriggered = false;
    }

    private void drawBoard() {
        char file = 'a';
        int rank = 8;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String currentTag = (String) (Character.toString(file) + Integer.toString(rank));
                int viewID = getResources().getIdentifier(currentTag, "id", getPackageName());
                ImageView currentSpace = (ImageView) findViewById(viewID);

                Piece currentPiece = board.space[row][col].piece;
                if (currentPiece == null) {
                    currentSpace.setImageResource(R.drawable.nulli);
                } else if (currentPiece.toString().equals("wp")) {
                    currentSpace.setImageResource(R.drawable.wpawn);
                } else if (currentPiece.toString().equals("bp")) {
                    currentSpace.setImageResource(R.drawable.bpawn);
                } else if (currentPiece.toString().equals("wR")) {
                    currentSpace.setImageResource(R.drawable.wrook);
                } else if (currentPiece.toString().equals("bR")) {
                    currentSpace.setImageResource(R.drawable.brook);
                } else if (currentPiece.toString().equals("wN")) {
                    currentSpace.setImageResource(R.drawable.wknight);
                } else if (currentPiece.toString().equals("bN")) {
                    currentSpace.setImageResource(R.drawable.bknight);
                } else if (currentPiece.toString().equals("wB")) {
                    currentSpace.setImageResource(R.drawable.wbishop);
                } else if (currentPiece.toString().equals("bB")) {
                    currentSpace.setImageResource(R.drawable.bbishop);
                } else if (currentPiece.toString().equals("wQ")) {
                    currentSpace.setImageResource(R.drawable.wqueen);
                } else if (currentPiece.toString().equals("bQ")) {
                    currentSpace.setImageResource(R.drawable.bqueen);
                } else if (currentPiece.toString().equals("wK")) {
                    currentSpace.setImageResource(R.drawable.wking);
                } else if (currentPiece.toString().equals("bK")) {
                    currentSpace.setImageResource(R.drawable.bking);
                }
                file = (char) (file + 1);
            }
            rank = rank - 1;
            file = 'a';
        }
    }
}