package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Pawn extends Piece {

    Pawn(Color color) {
        super(color);
    }

	public Error canMove(Coordinate origin, Coordinate target){
        if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
        }
        if (origin.diagonalDistance(target) >= 3) {
			return Error.BAD_DISTANCE;
        }
        if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
        }
        return null;
    }


}