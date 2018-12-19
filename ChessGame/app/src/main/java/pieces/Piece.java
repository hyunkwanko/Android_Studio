package pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinate;

public class Piece {

	public char color;
	public boolean moved;

	public Piece(){
		this.color = 'w';
		this.moved = false;
	}

	public Piece(char color) {
		this.color = color;
		this.moved = false;
	}

	public ArrayList<Coordinate> getMoves(Board board, Coordinate start) {
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		return moves;
	}

	public ArrayList<Coordinate> straightMoves(ArrayList<Coordinate> moves, Board board, Coordinate start) {
		int i = start.col - 1;

		while (i > -1) {
			if (board.space[start.row][i].piece == null) {
				moves.add(new Coordinate(start.row, i));
			} else {
				moves.add(new Coordinate(start.row, i));
				break;
			}
			i--;
		}

		i = start.col + 1;
		while (i < 8) {
			if (board.space[start.row][i].piece == null) {
				moves.add(new Coordinate(start.row, i));
			} else {
				moves.add(new Coordinate(start.row, i));
				break;
			}
			i++;
		}
		i = start.row - 1;
		while (i > -1) {
			if (board.space[i][start.col].piece == null) {
				moves.add(new Coordinate(i, start.col));
			} else {
				moves.add(new Coordinate(i, start.col));
				break;
			}
			i--;
		}

		i = start.row + 1;
		while (i < 8) {
			if (board.space[i][start.col].piece == null) {
				moves.add(new Coordinate(i, start.col));
			} else {
				moves.add(new Coordinate(i, start.col));
				break;
			}
			i++;
		}

		return moves;
	}

	public ArrayList<Coordinate> diagonalMoves(ArrayList<Coordinate> moves, Board board, Coordinate start) {
		int row = start.row - 1;
		int col = start.col - 1;
		while (row > -1 && col > -1) {
			if (board.space[row][col].piece == null) {
				moves.add(new Coordinate(row, col));
			} else {
				moves.add(new Coordinate(row, col));
				break;
			}
			row--;
			col--;
		}

		row = start.row - 1;
		col = start.col + 1;
		while (row > -1 && col < 8) {
			if (board.space[row][col].piece == null) {
				moves.add(new Coordinate(row, col));
			} else {
				moves.add(new Coordinate(row, col));
				break;
			}
			row--;
			col++;
		}

		row = start.row + 1;
		col = start.col - 1;
		while (row < 8 && col > -1) {
			if (board.space[row][col].piece == null) {
				moves.add(new Coordinate(row, col));
			} else {
				moves.add(new Coordinate(row, col));
				break;
			}
			row++;
			col--;
		}

		row = start.row + 1;
		col = start.col + 1;
		while (row < 8 && col < 8) {
			if (board.space[row][col].piece == null) {
				moves.add(new Coordinate(row, col));
			} else {
				moves.add(new Coordinate(row, col));
				break;
			}
			row++;
			col++;
		}

		return moves;

	}

	public ArrayList<Coordinate> deleteIllegal(ArrayList<Coordinate> moves, Board board, Coordinate start) {
		for (int i = 0; i < moves.size(); i++) {
			if (!inBounds(moves.get(i))) {
				moves.remove(i);
				i--;
			} else if (board.space[moves.get(i).row][moves.get(i).col].piece != null) {
				if (board.space[start.row][start.col].piece.color == board.space[moves.get(i).row][moves
						.get(i).col].piece.color) {
					moves.remove(i);
					i--;
				}
			}
		}
		return moves;
	}

	private static boolean inBounds(Coordinate move) {
		if ((move.row > -1) && (move.row < 8) && (move.col > -1) && (move.col < 8)) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "";
	}
}
