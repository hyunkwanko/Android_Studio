package chess;

import java.util.ArrayList;
import pieces.*;

public class Action {
	public static boolean movePiece(boolean isWhiteTurn, Board board, Coordinate start, Coordinate end, String pc) {
		Piece selPiece = board.space[start.row][start.col].piece;

		if (selPiece == null)
			return false;

		if (isWhiteTurn) {
			if (selPiece.color != 'w') {
				return false;
			}
		} else {
			if (selPiece.color != 'b') {
				return false;
			}
		}

		if (specialMove(board, start, end)) {
			return true;
		}

		ArrayList<Coordinate> moves = selPiece.getMoves(board, start);

		if (moves.contains(end)) {
			board.update(start, end, "");
			if(!Winner.inCheck(board, !isWhiteTurn)) {
				board.undo();
				return true;
			}
			board.undo();
		}
		return false;
	}

	private static boolean specialMove(Board board, Coordinate start, Coordinate end) {
		Piece selPiece = board.space[start.row][start.col].piece;

		if (selPiece instanceof Pawn) {
			if ((selPiece.color == 'w' && start.row == 3) || ((selPiece.color == 'b' && start.row == 4))) {
				int offset = 1;
				if (selPiece.color == 'w') {
					offset = -1;
				}
				if (start.col != 7) {
					if (enPasse(board, start, end, selPiece, offset, 1)) {
						return true;
					}
				}
				if (start.col != 0) {
					if (enPasse(board, start, end, selPiece, offset, -1)) {
						return true;
					}
				}
			}
		}

		if (selPiece instanceof King) {
			if (!selPiece.moved) {
				if(castleingClear(board, start, end)){
					if (end.col == (start.col + 2)) {
						board.space[end.row][7].piece = null;
						board.space[end.row][5].piece = new Rook(selPiece.color);
						board.space[end.row][5].piece.moved = true;
					}else{
						board.space[end.row][0].piece = null;
						board.space[end.row][2].piece = new Rook(selPiece.color);
						board.space[end.row][2].piece.moved = true;
					}
					return true;
				}
			}
		}

		return false;
	}

	private static boolean enPasse(Board board, Coordinate start, Coordinate end, Piece selPiece, int offset,
			int direction) {
		Coordinate right = new Coordinate(start.row, start.col + direction);
		Coordinate rightStart = new Coordinate(start.row + (2 * offset), start.col + direction);
		if (board.lastUpdatedStart.equals(rightStart)) {
			if (board.lastUpdatedEnd.equals(right)) {
				if ((board.space[right.row][right.col].piece.color != selPiece.color)
						&& board.space[right.row][right.col].piece instanceof Pawn) {
					if ((end.row == (start.row + (1 * offset))) && (end.col == (start.col + direction))) {
						board.space[right.row][right.col].piece = null;
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean castleingClear(Board board, Coordinate start, Coordinate end) {
		if ((end.row == (start.row))) {
			int colPstart = -1;
			int colPend = -1;
			
			if (end.col == (6)) {
				if (board.space[end.row][7].piece != null) {
					if (!board.space[end.row][7].piece.moved) {
						colPstart = 5;
						colPend = 7;
					}
				}
			} else if (end.col == (1)) {
				if (board.space[end.row][0].piece != null) {
					if (!board.space[end.row][0].piece.moved) {
						colPstart = 1;
						colPend = 4;
					}
				}
			}
			
			if (colPstart != -1) {
					for (int i = colPstart; i < colPend; i++) {
						if (board.space[start.row][i].piece != null) {
							return false;
						}
					}
				return true;
			}
		}
		return false;
	}
}