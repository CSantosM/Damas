package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class PlayControllerTest {

    private PlayController playController;
    private Game game;

    public PlayControllerTest() {
        game = new Game();
        playController = new PlayController( game, new State());
    }

    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        assertNull(playController.move(origin, target));
        assertNull(playController.getPiece(origin));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    private Error advance(Coordinate[][] coordinates){
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = playController.move(coordinates[i][0], coordinates[i][1]);
        }
        return error;
    }

    private void boardWithOneBlackPiece(){
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < game.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Piece piece= game.getPiece(coordinate);
                if (!coordinate.equals(new Coordinate(2,3)) && piece != null){
                    game.getBoard().remove(coordinate);
                }
            }
        }
	}

    @Test
    public void testGivenGameWhenMovementEatLastPieceThenOk() {
        boardWithOneBlackPiece();
        assertNull(this.advance(new Coordinate[][] {
            { new Coordinate(5, 0), new Coordinate(4, 1) },
            { new Coordinate(2, 3), new Coordinate(3, 2) },
            { new Coordinate(4, 1), new Coordinate(2, 3) },
        }));
        assertTrue(playController.isBlocked());
    }
}