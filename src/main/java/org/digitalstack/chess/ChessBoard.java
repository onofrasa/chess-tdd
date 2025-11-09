package org.digitalstack.chess;

public class ChessBoard {

    public static int BOARD_WIDTH = 7;
    public static int BOARD_HEIGHT = 7;

    private final Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[BOARD_WIDTH][BOARD_HEIGHT];
    }

    public void add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        boolean validStartingPoint = pieceColor.equals(PieceColor.WHITE) && xCoordinate == 1 ||
                pieceColor.equals(PieceColor.BLACK) && xCoordinate == 6;

        if (validStartingPoint) {
            if (pieces[xCoordinate][yCoordinate] == null) {
                pawn.setXCoordinate(xCoordinate);
                pawn.setYCoordinate(yCoordinate);
                pieces[xCoordinate][yCoordinate] = pawn;
            } else {
                System.out.println("Pawn already present on x: " + xCoordinate + ", y: " + yCoordinate);
                setInvalidCoordinates(pawn);
            }
        } else {
            System.out.println("Invalid starting point for pawn");
            setInvalidCoordinates(pawn);
        }
    }

    private void setInvalidCoordinates(Pawn pawn) {
        pawn.setXCoordinate(-1);
        pawn.setYCoordinate(-1);
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate <= 7 && yCoordinate >= 0 && yCoordinate <= 7;
    }
}
