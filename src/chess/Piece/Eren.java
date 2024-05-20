package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import static chess.Chess.gameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Eren extends Piece {

    public Eren(Color color) {
        super(color);

        if (color == Color.WHITE) {
            super.setImagePath("/assets/eren_blue.png");
        } else {
            super.setImagePath("/assets/eren.png");
        }

        setImage(new Image(this.imagePath));

        super.setId("Eren");
    }

    @Override
    public boolean VerifyTile(Tile t) {

        int posYM = t.get_PosY();
        int posXM = t.get_PosX();
        int posYP = getCurrentTile().get_PosY();
        int posXP = getCurrentTile().get_PosX();

        return (Math.abs(posYM - posYP) <= 1 && Math.abs(posXM - posXP) <= 1);
    }

    @Override
    public boolean CanMove(Tile t) {

        String direction = gameController.GetRelativePath(t);

        switch (direction) {

            case "South":
            case "West":
            case "North":
            case "East":
                return VerifyTile(t);

            default:
                return false;
        }
    }

    @Override
    public boolean CanKill(Piece p) {
        if (p.getColor() == this.color) {
            gameController.killFriend();
            return false;
        }
        return !(p.getId().contains("Saitama"));
    }

    @Override
    public boolean Kill(Piece p) {

        if (CanKill(p)) {

            board.getBoardAsGrid().getChildren().remove(p);
            p.getCurrentTile().setCurrentPiece(null);
            p.setCurrentTile(null);
            return true;

        }

        return false;

    }

    @Override
    public void Skill(Tile t) {

        for (int i = getCurrentTile().get_PosX() - 1; i <= getCurrentTile().get_PosX() + 1; i++) {
            for (int j = getCurrentTile().get_PosY() - 1; j <= getCurrentTile().get_PosY() + 1; j++) {

                if (i == getCurrentTile().get_PosX() && j == getCurrentTile().get_PosY()) {
                    continue;
                }

                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    continue;
                }

                if (board.getMatrix()[i][j].getCurrentPiece() != null && !(t.getCurrentPiece().getId().contains("Saitama") )) {
                    board.getBoardAsGrid().getChildren().remove(board.getMatrix()[i][j].getCurrentPiece());
                    board.getMatrix()[i][j].getCurrentPiece().setCurrentTile(null);
                    board.getMatrix()[i][j].setCurrentPiece(null);
                }
            }
        }

    }

}
