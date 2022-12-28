package ru.vsu.cs.baturin_v_a;

public class Pawn extends AbstractPiece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (this.isWhite) {
            System.out.print("wP");
        }
        if (!(this.isWhite)) {
            System.out.print("bP");

        }

    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        if (isWhite) {
            return ((srcCol == destCol) && (srcRow - destRow == 2) ||
                    ((srcRow - destRow == 1) && (Math.abs(srcCol - destCol) == 1)));
        } else {
            return ((srcCol == destCol) && (srcRow - destRow == -2) ||
                    ((srcRow - destRow == -1) && (Math.abs(srcCol - destCol) == 1)));
        }
    }
}
