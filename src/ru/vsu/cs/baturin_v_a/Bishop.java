package ru.vsu.cs.baturin_v_a;

public class Bishop extends AbstractPiece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite) {
            System.out.print("wB");
        } else {
            System.out.print("bB");
        }
    }

    private static Boolean diagonalPath(int srcRow, int srcCol,
                                        int destRow, int destCol) {
        return (((Math.abs(destCol - srcCol) == 1) && (Math.abs(destRow - srcRow) == 3)) ||
                ((Math.abs(destCol - srcCol) == 2) && (Math.abs(destRow - srcRow) == 6)));
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return diagonalPath(srcRow, srcCol, destRow, destCol) ||
                ((destRow == srcRow) && (Math.abs(destCol - srcCol) == 2));
    }
}
