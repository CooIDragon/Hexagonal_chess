package ru.vsu.cs.baturin_v_a;

public class Queen extends AbstractPiece {

    public Queen(boolean isWhite) {
        super(isWhite);

    }

    @Override
    public void draw() {
        if (isWhite){
            System.out.print("wQ");
        }
        else{
            System.out.print("bQ");
        }
    }

    private static boolean isNumEven(int x) {
        return x % 2 == 0;
    }

    private static Boolean diagonalPath(int srcRow, int srcCol,
                                        int destRow, int destCol) {
        return (((Math.abs(destCol - srcCol) == 1) && (Math.abs(destRow - srcRow) == 3)) ||
                ((Math.abs(destCol - srcCol) == 2) && (Math.abs(destRow - srcRow) == 6)));
    }

    private static Boolean straightPath(int srcRow, int srcCol,
                                        int destRow, int destCol) {
        return (isNumEven(Math.abs(destRow - srcRow)) && (destCol == srcCol)) ||
                (Math.abs(destCol - srcCol) == 1 && Math.abs(destRow - srcRow) == 1);
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return (diagonalPath(srcRow, srcCol, destRow, destCol))
                || straightPath(srcRow, srcCol, destRow, destCol) ||
                ((destRow == srcRow) && (Math.abs(destCol - srcCol) == 2));
    }
}