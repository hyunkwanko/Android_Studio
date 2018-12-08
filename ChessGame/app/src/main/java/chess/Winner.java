package chess;

import java.util.ArrayList;
import pieces.*;

public class Winner {
	public static boolean inCheck(Board board, boolean isWhiteTurn) {
		ArrayList<Coordinate> whiteMoves = new ArrayList<Coordinate>();
		ArrayList<Coordinate> blackMoves = new ArrayList<Coordinate>();
		Coordinate whiteKing = board.whiteKing;
		Coordinate blackKing = board.blackKing;

		resetTerritories(board);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece piece = board.space[row][col].piece;
				if (piece != null) {
					ArrayList<Coordinate> pieceMoves = piece.getMoves(board, new Coordinate(row, col));
					if (piece.color == 'w') {
						if (pieceMoves.contains(blackKing)) {
							board.blackKingThreat.add(new Coordinate(row, col));
						}
						whiteMoves = mergeLists(whiteMoves, pieceMoves);
					} else {
						if (pieceMoves.contains(whiteKing)) {
							board.whiteKingThreat.add(new Coordinate(row, col));
						}
						blackMoves = mergeLists(blackMoves, pieceMoves);
					}
				}
			}
		}

		for (int i = 0; i < whiteMoves.size(); i++) {
			board.space[whiteMoves.get(i).row][whiteMoves.get(i).col].wTerritory = true;
		}

		for (int i = 0; i < blackMoves.size(); i++) {
			board.space[blackMoves.get(i).row][blackMoves.get(i).col].bTerritory = true;
		}

		if ((board.space[blackKing.row][blackKing.col].wTerritory == true)&& isWhiteTurn) {
			return true;
		}

		if ((board.space[whiteKing.row][whiteKing.col].bTerritory == true)&& !isWhiteTurn) {
			return true;
		}

		return false;
	}

	public static boolean checkmate(Board board, boolean isWhiteTurn) {
		boolean checkmate = false;
		ArrayList<Coordinate> threats = new ArrayList<Coordinate>();
		Coordinate king = new Coordinate();

		if (isWhiteTurn) {
			threats = board.blackKingThreat;
			king = board.blackKing;
		} else {
			threats = board.whiteKingThreat;
			king = board.whiteKing;
		}

		ArrayList<Coordinate> kingMoves = board.space[king.row][king.col].piece.getMoves(board, king);
		if (isWhiteTurn) {
			for (int i = 0; i < kingMoves.size(); i++) {
				if (board.space[kingMoves.get(i).row][kingMoves.get(i).col].wTerritory) {
					kingMoves.remove(i);
					i--;
				}
			}
		} else {
			for (int i = 0; i < kingMoves.size(); i++) {
				if (board.space[kingMoves.get(i).row][kingMoves.get(i).col].bTerritory) {
					kingMoves.remove(i);
					i--;
				}
			}
		}

		if (kingMoves.isEmpty()) {
			if (threats.size() > 1) {
				checkmate = true;
			}else if(isWhiteTurn && !board.space[threats.get(0).row][threats.get(0).col].bTerritory){
				checkmate = true;
			} else if(!isWhiteTurn && !board.space[threats.get(0).row][threats.get(0).col].wTerritory){
				checkmate = true;
			}else{
				checkmate = false;
			}
		}
		return checkmate;
	}

	private static ArrayList<Coordinate> mergeLists(ArrayList<Coordinate> moves, ArrayList<Coordinate> pieceMoves) {
		ArrayList<Coordinate> newmoves = new ArrayList<Coordinate>();
		newmoves.addAll(moves);
		newmoves.addAll(pieceMoves);
		return newmoves;
	}

	private static void resetTerritories(Board board) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board.space[row][col].wTerritory = false;
				board.space[row][col].bTerritory = false;
			}
		}
		board.whiteKingThreat.clear();
		board.blackKingThreat.clear();
	}
}
