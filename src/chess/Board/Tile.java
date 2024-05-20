package chess.Board;

import chess.Piece.Piece;
import static chess.Chess.TILE_W;
import static chess.Chess.TILE_Y;
import static chess.Chess.gameController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.JOptionPane;

public class Tile extends Rectangle {

    private final int width = TILE_W;
    private final int height = TILE_Y;
    private final int r;
    private final int c;
    private Color color;
    private Piece currentPiece;

    public Tile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public int get_PosX() {
        return r;
    }

    public int get_PosY() {
        return c;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Tile(Color c, int x, int y) {

        this.color = c;
        this.r = x;
        this.c = y;

        setFill(c);
        setHeight(this.height);
        setWidth(this.width);

        this.setOnMouseClicked((MouseEvent event) -> {

            if (gameController.getOriginTile() == null && (event.getButton() == MouseButton.PRIMARY)) {

                if (getCurrentPiece() != null) {

                    if (gameController.getRound() % 2 == 0 && getCurrentPiece().getColor() == Color.WHITE) {
                        gameController.setOriginTile(this);
                    } else if (gameController.getRound() % 2 != 0 && getCurrentPiece().getColor() == Color.BLACK) {
                        gameController.setOriginTile(this);
                    } else {

                        if (gameController.getRound() % 2 == 0) {
                            JOptionPane.showMessageDialog(null, "VEZ DO JOGADOR 1!");
                        } else {
                            JOptionPane.showMessageDialog(null, "VEZ DO JOGADOR 2!");
                        }

                    }

                }

            } else if (gameController.getOriginTile() != null && (event.getButton() == MouseButton.PRIMARY)) {
                try {
                    gameController.setDestinyTile(Tile.this);
                } catch (IOException ex) {
                    Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (gameController.getOriginTile() != null && gameController.getOriginSkillTile() == null && (event.getButton() == MouseButton.SECONDARY)) {

                if (getCurrentPiece() != null) {

                    gameController.setOriginSkillTile(this);

                }

            } else if (gameController.getOriginSkillTile() != null && (event.getButton() == MouseButton.SECONDARY)) {

                gameController.setDestinySkillTile(this);

            }

        });

    }

}
