package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.HEIGHT;
import static chess.Chess.TILE_W;
import static chess.Chess.WIDTH;
import static chess.Chess.board;
import static chess.Chess.gameController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

public abstract class Piece extends ImageView {

    protected String id;
    protected Color color;
    protected String imagePath;
    protected Tile currentTile;
    protected Image pieceImage;

    public Piece(Color color) {
        this.color = color;

        this.setOnMouseClicked((event) -> {

            if (gameController.getOriginTile() == null && (event.getButton() == MouseButton.PRIMARY)) {

                if (gameController.getRound() % 2 == 0 && this.color == Color.WHITE) {
                    gameController.setOriginTile(this.currentTile);
                } else if (gameController.getRound() % 2 != 0 && this.color == Color.BLACK) {
                    gameController.setOriginTile(this.currentTile);
                } else {

                    if (gameController.getRound() % 2 == 0) {
                        JOptionPane.showMessageDialog(null, "VEZ DO JOGADOR 1!");
                    } else {
                        JOptionPane.showMessageDialog(null, "VEZ DO JOGADOR 2!");
                    }

                }

            } else if (gameController.getOriginTile() != null && (event.getButton() == MouseButton.PRIMARY)) {
                try {
                    gameController.setDestinyTile(this.currentTile);
                } catch (IOException ex) {
                    Logger.getLogger(Piece.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (gameController.getOriginTile() != null && gameController.getOriginSkillTile() == null && (event.getButton() == MouseButton.SECONDARY)) {
                gameController.setOriginSkillTile(this.currentTile);
            } else if (gameController.getOriginSkillTile() != null && (event.getButton() == MouseButton.SECONDARY)) {
                gameController.setDestinySkillTile(this.currentTile);
            }

        });

    }

    public boolean CanMove(Tile t) {

        return t != getCurrentTile();

    }

    public boolean VerifyTile(Tile t) {
        return false;
    }

    public void Skill(Tile t) {
    }

    public void Move(Tile t) throws IOException {

        if (CanMove(t)) {

            String direction = gameController.GetRelativePath(t);

            if (t.getCurrentPiece() != null) {

                if (Kill(t.getCurrentPiece())) {

                    setCurrentTile(t);
                    putPieceOnTile(t);

                    gameController.resetAll();
                    gameController.setRound(gameController.getRound() + 1);
                    System.out.println(this.getId() + " moving " + direction + "!");
                    System.out.println("Rodada: " + gameController.getRound());

                } else {

                    JOptionPane.showMessageDialog(null, getId() + " can't kill the " + t.getCurrentPiece().getId());
                    gameController.resetAll();

                }

            } else {

                setCurrentTile(t);
                putPieceOnTile(t);

                gameController.resetAll();
                gameController.setRound(gameController.getRound() + 1);
                System.out.println(this.getId() + " moving " + direction + "!");
                System.out.println("Rodada: " + gameController.getRound());

            }

        }

        gameController.resetAll();
        boolean endW = gameController.endGame(Color.WHITE);
        boolean endB = gameController.endGame(Color.BLACK);
        if (endW && endB) {
            gameController.finish(1);
        } else if (endW) {
            gameController.finish(2);
        } else if (endB) {
            gameController.finish(3);
        }

    }

    public boolean CanKill(Piece p) {
        return true;
    }

    public boolean Kill(Piece p) {
        board.getBoardAsGrid().getChildren().remove(p);
        p.getCurrentTile().setCurrentPiece(null);
        p.setCurrentTile(null);
        return true;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {

        if (this.currentTile != null) {
            this.currentTile.setCurrentPiece(null);
        }

        this.currentTile = currentTile;

    }

    public Image getPieceImage() {
        return pieceImage;
    }

    public void setPieceImage(Image pieceImage) {
        this.pieceImage = pieceImage;
        setImage(pieceImage);
    }

    public void putPieceOnTile(Tile t) {

        setCurrentTile(t);
        getCurrentTile().setCurrentPiece(this);
        board.getBoardAsGrid().getChildren().remove(this);
        board.getBoardAsGrid().add(this, t.get_PosY(), t.get_PosX());

        if (this.getId().equals("Goku")) {
            this.setTranslateX(TILE_W / 5);
        }

    }

    public void Path() {

        for (int r = 0; r < WIDTH; r++) {
            for (int c = 0; c < HEIGHT; c++) {
                if (CanMove(board.getMatrix()[r][c])) {
                    board.getMatrix()[r][c].setFill(Color.RED);
                    board.getMatrix()[r][c].setStroke(Color.BLACK);
                }
            }
        }

    }

    public void Unpath() {

        for (int r = 0; r < WIDTH; r++) {
            for (int c = 0; c < HEIGHT; c++) {
                if (r % 2 == 0) {

                    if (c % 2 == 0) {
                        board.getMatrix()[r][c].setFill(Color.ANTIQUEWHITE);
                        board.getMatrix()[r][c].setStroke(Color.TRANSPARENT);
                    } else {
                        board.getMatrix()[r][c].setFill(Color.DARKGREEN);
                        board.getMatrix()[r][c].setStroke(Color.TRANSPARENT);
                    }

                } else {

                    if (c % 2 == 0) {
                        board.getMatrix()[r][c].setFill(Color.DARKGREEN);
                        board.getMatrix()[r][c].setStroke(Color.TRANSPARENT);
                    } else {
                        board.getMatrix()[r][c].setFill(Color.ANTIQUEWHITE);
                        board.getMatrix()[r][c].setStroke(Color.TRANSPARENT);
                    }
                }
            }
        }
    }

}
