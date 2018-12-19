package chess;

import java.util.ArrayList;
import pieces.*;

public class Board {

    public Space[][] space;
    public Coordinate lastUpdatedStart;
    public Coordinate lastUpdatedEnd;
    public Piece lastPieceStart;
    public Piece lastPieceEnd;
    public ArrayList<Coordinate> whiteKingThreat;
    public ArrayList<Coordinate> blackKingThreat;
    public Coordinate whiteKing;
    public Coordinate blackKing;
    public Coordinate lastWhiteKing;
    public Coordinate lastBlackKing;

    public class Space {

        public String color;
        public Piece piece = null;

        boolean wTerritory = false;
        boolean bTerritory = false;

        public Space(String color, Piece piece) {
            this.color = color;
            this.piece = piece;
        }
    }

    public Board() {
        this.space = new Space[9][9];
        this.lastUpdatedStart = new Coordinate(-1, -1);
        this.lastUpdatedEnd = new Coordinate(-1, -1);
        this.lastPieceStart = new Piece();
        this.lastPieceEnd = new Piece();
        this.whiteKingThreat = new ArrayList<Coordinate>();
        this.blackKingThreat = new ArrayList<Coordinate>();
        this.whiteKing = new Coordinate(7, 4);
        this.blackKing = new Coordinate(0, 4);
        this.lastWhiteKing = new Coordinate(7, 4);
        this.lastBlackKing = new Coordinate(0, 4);
    }

    public void init() {
        boolean white = false;

        space[0][0] = new Space("   ", new Rook('b'));
        space[0][1] = new Space("## ", new Knight('b'));
        space[0][2] = new Space("   ", new Bishop('b'));
        space[0][3] = new Space("## ", new Queen('b'));
        space[0][4] = new Space("   ", new King('b'));
        space[0][5] = new Space("## ", new Bishop('b'));
        space[0][6] = new Space("   ", new Knight('b'));
        space[0][7] = new Space("## ", new Rook('b'));

        for (int col = 0; col < 8; col++) {
            if (white) {
                space[1][col] = new Space("   ", new Pawn('b'));
                white = false;
            } else {
                space[1][col] = new Space("## ", new Pawn('b'));
                white = true;
            }
        }

        for (int row = 2; row < 6; row++) {
            white = !white;
            for (int col = 0; col < 8; col++) {
                if (white) {
                    space[row][col] = new Space("   ", null);
                    white = false;
                } else {
                    space[row][col] = new Space("## ", null);
                    white = true;
                }
            }
        }

        white = !white;
        for (int col = 0; col < 8; col++) {
            if (white) {
                space[6][col] = new Space("   ", new Pawn('w'));
                white = false;
            } else {
                space[6][col] = new Space("## ", new Pawn('w'));
                white = true;
            }
        }

        space[7][0] = new Space("## ", new Rook('w'));
        space[7][1] = new Space("   ", new Knight('w'));
        space[7][2] = new Space("## ", new Bishop('w'));
        space[7][3] = new Space("   ", new Queen('w'));
        space[7][4] = new Space("## ", new King('w'));
        space[7][5] = new Space("   ", new Bishop('w'));
        space[7][6] = new Space("## ", new Knight('w'));
        space[7][7] = new Space("   ", new Rook('w'));

        for (int row = 0; row < 8; row++) {
            space[row][8] = new Space((" " + ("" + (8 - row))), null);
        }

        for (int i = 0; i < 8; i++) {
            char col = 'a';
            space[8][i] = new Space(" " + Character.toString((char) (col + i)) + " ", null);
        }

        space[8][8] = new Space("  ", null);
    }

    public void update(Coordinate start, Coordinate end, String pawnCommand) {
        lastPieceStart = space[start.row][start.col].piece;
        lastPieceEnd = space[end.row][end.col].piece;
        lastWhiteKing = whiteKing;
        lastBlackKing = blackKing;

        Piece startP = space[start.row][start.col].piece;

        if (startP instanceof Pawn && ((end.row == 0) || (end.row == 7))) {
            if (startP.color == 'w') {
                space[end.row][end.col].piece = promotePawn('w', pawnCommand);
            } else if (startP.color == 'b') {
                space[end.row][end.col].piece = promotePawn('b', pawnCommand);
            }
        } else {
            space[end.row][end.col].piece = startP;
            if (startP instanceof King) {
                if (startP.color == 'w') {
                    whiteKing = new Coordinate(end.row, end.col);
                } else {
                    blackKing = new Coordinate(end.row, end.col);
                }
            }
            space[end.row][end.col].piece.moved = true;
            lastUpdatedEnd = new Coordinate(end.row, end.col);
        }
        space[start.row][start.col].piece = null;
        lastUpdatedStart = new Coordinate(start.row, start.col);
    }

    private Piece promotePawn(char color, String pawnCommand) {
        if (pawnCommand.equals("R")) {
            return new Rook(color);
        }
        if (pawnCommand.equals("N")) {
            return new Knight(color);
        }
        if (pawnCommand.equals("B")) {
            return new Bishop(color);
        }
        return new Queen(color);
    }

    public void undo() {
        space[lastUpdatedStart.row][lastUpdatedStart.col].piece = lastPieceStart;
        space[lastUpdatedEnd.row][lastUpdatedEnd.col].piece = lastPieceEnd;
        whiteKing = lastWhiteKing;
        blackKing = lastBlackKing;
    }

    public void printBoard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (space[i][j].piece == null)
                    System.out.print(space[i][j].color);
                else
                    System.out.print(space[i][j].piece.toString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
