package ru.vsu.cs.baturin_v_a;

public class King extends AbstractPiece {

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite) {
            System.out.print("wK");
        } else {
            System.out.print("bK");
        }
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return ((destCol == srcCol && Math.abs(destRow - srcRow) == 2) ||
                (destRow == srcRow && Math.abs(destCol - srcCol) == 2) ||
                (Math.abs(destCol - srcCol) == 1 && Math.abs(destRow - srcRow) == 1) ||
                (Math.abs(destCol - srcCol) == 1 && Math.abs(destRow - srcRow) == 3));
    }
}
