package chess;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chess {
	public static void main(String[] args) throws FileNotFoundException {
		int winner = 0;

		boolean drawOffered = false;
		boolean isWhiteTurn = true;
		Coordinate illegalCoordinate = new Coordinate(-1, -1);

		Scanner sc = new Scanner(System.in);

		Board board = new Board();
		board.init();
		board.printBoard();

		while (winner == 0) {
			
			if (isWhiteTurn)
				System.out.print("White's Move: ");
			else
				System.out.print("Black's Move: ");
			String command = sc.nextLine();
			System.out.println();

			if (command.equals("resign") && isWhiteTurn) {
				winner = 2;
				break;
			} else if (command.equals("resign")) {
				winner = 1;
				break;
			}

			if (command.equals("draw") && drawOffered) {
				winner = 3;
				break;
			} else if (drawOffered) {
				drawOffered = false;
			}

			String[] spaces = command.split(" ");
			String pawnCommand = "";
			if (spaces.length == 3) {
				if (spaces[2].equals("draw?")) {
					drawOffered = true;
					continue;
				}
				pawnCommand = spaces[2];
			} else if (spaces.length != 2) {// not enough commands entered
				System.out.println("Illegal move, try again\n");
				continue;
			}

			Coordinate start = new Coordinate(spaces[0]);
			Coordinate end = new Coordinate(spaces[1]);
			if (start.equals(illegalCoordinate) || end.equals(illegalCoordinate)) {
				System.out.println("Illegal move, try again\n");
				continue;
			}

			if (!Action.movePiece(isWhiteTurn, board, start, end, pawnCommand)) {
				System.out.println("Illegal move, try again\n");
				continue;
			}

			board.update(start, end, pawnCommand);
			board.printBoard();

			if (Winner.inCheck(board, isWhiteTurn)) {
				if (Winner.checkmate(board, isWhiteTurn)) {
					System.out.println("Checkmate\n");
					if(isWhiteTurn){
						winner = 1;
						break;
					}else{
						winner = 2;
						break;
					}
				} else {
					System.out.println("Check\n");
				}
			}

			isWhiteTurn = !isWhiteTurn;
		}

		if (winner == 1) {
			System.out.println("White wins");
		} else if (winner == 2) {
			System.out.println("Black wins");
		} else if (winner == 3) {
			System.out.println("Draw");
		}
		sc.close();
	}
}
