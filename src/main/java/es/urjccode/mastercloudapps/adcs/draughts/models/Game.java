package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public class Game {

	private Board board;
	private Turn turn;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.getBoard().getDimension(); i++) {
			for (int j = 0; j < this.getBoard().getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.getBoard().put(coordinate, piece);
				}
			}
		}
	}

	private Piece getInitialPiece(Coordinate coordinate) {
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			if (row <= 2) {
				return new Pawn(Color.BLACK);
			}
			if (row >= 5) {
				return new Pawn(Color.WHITE);
			}
		}
		return null;
	}

	public void move(Coordinate origin, Coordinate target) {
		assert origin != null && target != null;
		this.getBoard().move(origin, target);
		this.turn.change();
	}

	public Color getColor(Coordinate coordinate) {
		return this.getBoard().getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.getBoard() + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.getBoard().getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.getPieces(this.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.getBoard().getDimension();
	}

	public List<Piece> getPieces(Color color) {
		return this.getBoard().getPieces(color);
	}

	public Error isValidMovement(Coordinate origin, Coordinate target) {
		return this.getBoard().isValidMovement(origin, target, this.getColor());
	}

	public Board getBoard() {
		return this.board;
	}
}