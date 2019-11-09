package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece {

	private Color color;

	Piece(Color color){
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isAdvanced(Coordinate origin, Coordinate target) {
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE){
			return difference>0;
		}
		return difference<0;
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