package org.digitalstack.chess;

import static org.digitalstack.chess.MovementType.CAPTURE;
import static org.digitalstack.chess.MovementType.MOVE;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void move(MovementType movementType, int newX, int newY) {
        /**
         * I implemented the method so that all tests pass, but I believe
         * testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates makes an illegal move and should not pass.
         * In testLimits_The_Number_Of_Pawns, all pawns are positioned at X = 6.
         * Black pawns should move forward along the X axis (X6 → X5 → X4, etc.)
         * and should not move along the Y axis, as that would indicate a lateral move.
         * Therefore, in this method, for a MOVE action, I think only X should change — not Y.
         */
        int currentYCoordinate = getYCoordinate();
        int currentXCoordinate = getXCoordinate();
        int direction = newY - currentYCoordinate;
        int expectedDirection = getPieceColor().equals(PieceColor.BLACK) ? -1 : 1;

        if (movementType.equals(MOVE)) {
            if ((newX == currentXCoordinate) && (direction == expectedDirection)) {
                setYCoordinate(newY);
            } else {
                System.out.println("Invalid move!");
            }
        } else if (movementType.equals(CAPTURE)) {
            if ((Math.abs(newX - currentXCoordinate) == 1) && (direction == expectedDirection)) {
                setYCoordinate(newY);
                setXCoordinate(newX);
            } else {
                System.out.println("Invalid capture!");
            }
        }
    }

    @Override
    public String toString() {
        return currentPositionAsString();
    }

    protected String currentPositionAsString() {
        return String.format("Current X: %s\nCurrent Y: %s\nPiece Color: %s", xCoordinate, yCoordinate, pieceColor);
    }
}
