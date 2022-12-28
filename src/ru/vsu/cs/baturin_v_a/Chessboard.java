package ru.vsu.cs.baturin_v_a;

import java.util.Scanner;

public class Chessboard {

    private Boolean gameRunning;
    private final AbstractPiece[][] chessboard = new AbstractPiece[numOfRows][numOfCols];
    Scanner user_input = new Scanner(System.in);
    private static final int numOfRows = 21;
    private static final int numOfCols = 11;
    private static int srcRow, srcCol, destRow, destCol;
    private static Boolean whitesTurnToMove;
    private static Boolean invalidMove = false;
    private static King wKing = new King(true);
    private static King bKing = new King(false);
    String move;

    public Chessboard() {
        initialiseBoard(chessboard);
        gameRunning = true;
    }

    public Boolean getGameRunning() {
        return this.gameRunning;
    }

    private static void initialiseBoard(AbstractPiece[][] chessboard) {

        chessboard[0][5] = new Bishop(false);
        chessboard[20][5] = new Bishop(true);

        chessboard[2][5] = new Bishop(false);
        chessboard[18][5] = new Bishop(true);

        chessboard[4][5] = new Bishop(false);
        chessboard[16][5] = new Bishop(true);

        chessboard[1][6] = bKing;
        chessboard[19][6] = wKing;

        chessboard[1][4] = new Queen(false);
        chessboard[19][4] = new Queen(true);

        chessboard[2][3] = new Knight(false);
        chessboard[18][3] = new Knight(true);

        chessboard[2][7] = new Knight(false);
        chessboard[18][7] = new Knight(true);

        chessboard[3][2] = new Rook(false);
        chessboard[17][2] = new Rook(true);

        chessboard[3][8] = new Rook(false);
        chessboard[17][8] = new Rook(true);

        int i = 4;
        int j = 1;

        while ((i < 9) && (j < 6)) {
            chessboard[i][j] = new Pawn(false);
            i++;
            j++;
        }

        i -= 2;

        while ((i > 3) && (j < 10)) {
            chessboard[i][j] = new Pawn(false);
            i--;
            j++;
        }

        i = 16;
        j = 1;

        while ((i > 11) && (j < 6)) {
            chessboard[i][j] = new Pawn(true);
            i--;
            j++;
        }

        i += 2;

        while ((i < 17) && (j < 10)) {
            chessboard[i][j] = new Pawn(true);
            i++;
            j++;
        }

        whitesTurnToMove = true;
    }

    public void printBoard() {
        System.out.println("\ta\tb\tc\td\te\tf\tg\th\ti\tj\tk");
        for (int row = 0; row < chessboard.length; row++) {
            System.out.print(21 - row + ".\t");
            for (int col = 0; col < chessboard[row].length; col++) {
                if (chessboard[row][col] != null) {
                    chessboard[row][col].draw();
                    System.out.print("\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }

        System.out.println("Print your answer like this: f 9 to f 11");
    }

    private boolean moveValid() {
        if (srcRow < 0 || srcRow > 20 || srcCol < 0 || srcCol > 20 || destRow < 0
                || destRow > 20 || destCol < 0 || destCol > 20) {
            System.out.println("Move is outside the board");
            return false;
        }

        if ((srcRow < 5 && srcCol < 5 - srcRow) || (destRow < 5 && destCol < 5 - destRow)
                || (srcRow < 5 && srcCol > 5 + srcRow) || (destRow < 5 && destCol > 5 + destRow)
                || (srcRow > 15 && srcCol < srcRow - 15) || (destRow > 15 && destCol < destRow - 15)
                || (srcRow > 15 && srcCol > 25 - srcRow) || (destRow > 15 && destCol > 25 - destRow)) {
            System.out.println("Move is outside the board");
            return false;
        }

        if (chessboard[srcRow][srcCol] == null) {
            System.err.println("Origin is empty");
            return false;
        }

        if ((chessboard[srcRow][srcCol].isWhite && !whitesTurnToMove)
                || (!chessboard[srcRow][srcCol].isWhite && whitesTurnToMove)) {
            System.err.println("It's not your turn");
            return false;
        }

        if (!chessboard[srcRow][srcCol].isMoveValid(srcRow, srcCol, destRow,
                destCol)) {
            System.err.println("This piece doesn't move like that");
            return false;
        }

        if (chessboard[destRow][destCol] == null) {
            return true;
        }

        if (chessboard[srcRow][srcCol].isWhite
                && chessboard[destRow][destCol].isWhite) {
            System.err.println("White cannot land on white");
            return false;
        }

        if (!chessboard[srcRow][srcCol].isWhite
                && !chessboard[destRow][destCol].isWhite) {
            System.err.println("Black cannot land on black");
            return false;
        }

        return true;
    }

    private void checkWin() {
        if (chessboard[destRow][destCol] == null) {
            return;
        } else if (chessboard[destRow][destCol].equals(bKing)) {
            gameRunning = false;
            System.out.println("White wins!!! Thanks for playing.");
        } else if (chessboard[destRow][destCol].equals(wKing)) {
            gameRunning = false;
            System.out.println("Black wins!!! Thanks for playing.");
        }
    }

    public void move() {
        if (invalidMove) {
            System.err.println("Move is invalid. Please try again:");
            invalidMove = false;
        } else if (whitesTurnToMove) {
            System.out
                    .println("""
                            ___________________________________________________
                            White's turn to move
                            ___________________________________________________
                            """);
        } else {
            System.out
                    .println("""
                            ___________________________________________________
                            Black's turn to move
                            ___________________________________________________
                            """);
        }

        move = user_input.nextLine();

        String lowerCase = move.toLowerCase();
        String[] components = lowerCase.split(" ");

        if (components.length != 5) {
            invalidMove = true;
            move();
            return;
        }

        srcRow = 20 - (Integer.parseInt(components[1]) - 1);
        srcCol = components[0].charAt(0) - 'a';
        destRow = 20 - (Integer.parseInt(components[4]) - 1);
        destCol = components[3].charAt(0) - 'a';

        if (moveValid()) {
            checkWin();
            chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
            chessboard[srcRow][srcCol] = null;
            whitesTurnToMove = !whitesTurnToMove;
        } else {
            invalidMove = true;
            move();
        }
    }

}
