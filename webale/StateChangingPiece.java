package webale;

public class StateChangingPiece {
    private Piece piece;

    public StateChangingPiece(Piece piece) {
        this.piece = piece;
    }

    public void setState(Piece piece) {
        this.piece = piece;
    }

    public Piece getState() {
        return piece;
    }
}