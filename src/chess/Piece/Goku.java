package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import static chess.Chess.gameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Goku extends Piece {

    int number;
    String colorBW;

    public Goku(Color color) {
        super(color);

        if (color == Color.WHITE) {
            super.setImagePath("/assets/goku_azul.png");
        } else {
            super.setImagePath("/assets/goku_red.png");
        }

        setImage(new Image(this.imagePath));

        super.setId("Goku");
    }

    @Override
    public boolean VerifyTile(Tile t) {

        int posYM = t.get_PosY();
        int posXM = t.get_PosX();
        int posYP = getCurrentTile().get_PosY();
        int posXP = getCurrentTile().get_PosX();

        return (Math.abs(posYM - posYP) <= 2 && Math.abs(posXM - posXP) <= 2);
    }

    @Override
    public boolean CanMove(Tile t) {
        if (t.getCurrentPiece() != null) {
            if (t.getCurrentPiece().getColor() == getColor()) {
                return false;
            }
        }

        String direction = gameController.GetRelativePath(t);

        switch (direction) {

            case "North":

                for (int i = getCurrentTile().get_PosX() - 1; i >= getCurrentTile().get_PosX() - 2; i--) {

                    if (i < 0 || i >= 10) {
                        break;
                    }

                    if (board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece() != null && board.getMatrix()[i][getCurrentTile().get_PosY()] != t) {
                        return false;
                    }

                    if (board.getMatrix()[i][getCurrentTile().get_PosY()] == t) {
                        return true;
                    }

                }

                return false;

            case "South":

                for (int i = getCurrentTile().get_PosX() + 1; i <= getCurrentTile().get_PosX() + 2; i++) {

                    if (i < 0 || i >= 10) {
                        break;
                    }

                    if (board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece() != null && board.getMatrix()[i][getCurrentTile().get_PosY()] != t) {
                        gameController.resetAll();
                        return false;
                    }

                    if (board.getMatrix()[i][getCurrentTile().get_PosY()] == t) {
                        return true;
                    }

                }

                return false;

            case "West":

                for (int i = getCurrentTile().get_PosY() - 1; i >= getCurrentTile().get_PosY() - 2; i--) {

                    if (i < 0 || i >= 10) {
                        break;
                    }

                    if (board.getMatrix()[getCurrentTile().get_PosX()][i].getCurrentPiece() != null && board.getMatrix()[getCurrentTile().get_PosY()][i] != t) {
                        gameController.resetAll();
                        return false;
                    }

                    if (board.getMatrix()[getCurrentTile().get_PosX()][i] == t) {
                        return true;
                    }

                }

                return false;

            case "East":

                for (int i = getCurrentTile().get_PosY() + 1; i <= getCurrentTile().get_PosY() + 2; i++) {

                    if (i < 0 || i >= 10) {
                        break;
                    }

                    if (board.getMatrix()[getCurrentTile().get_PosX()][i].getCurrentPiece() != null && board.getMatrix()[getCurrentTile().get_PosY()][i] != t) {
                        return false;
                    }

                    if (board.getMatrix()[getCurrentTile().get_PosX()][i] == t) {
                        return true;
                    }

                }

                return false;

            case "Northwest":

                for (int i = getCurrentTile().get_PosX() - 1, j = getCurrentTile().get_PosY() - 1; i >= getCurrentTile().get_PosX() - 2; i--, j--) {

                    if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                        break;
                    }

                    if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j] != t) {
                        return false;
                    }

                    if (board.getMatrix()[i][j] == t) {
                        return true;
                    }

                }

                return false;

            case "Northeast":

                for (int i = getCurrentTile().get_PosX() - 1, j = getCurrentTile().get_PosY() + 1; i >= getCurrentTile().get_PosX() - 2; i--, j++) {

                    if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                        break;
                    }

                    if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j] != t) {
                        return false;
                    }

                    if (board.getMatrix()[i][j] == t) {
                        return true;
                    }

                }

                return false;

            case "Southwest":

                for (int i = getCurrentTile().get_PosX() + 1, j = getCurrentTile().get_PosY() - 1; i <= getCurrentTile().get_PosX() + 2; i++, j--) {

                    if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                        break;
                    }

                    if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j] != t) {
                        return false;
                    }

                    if (board.getMatrix()[i][j] == t) {
                        return true;
                    }

                }

                return false;

            case "Southeast":

                for (int i = getCurrentTile().get_PosX() + 1, j = getCurrentTile().get_PosY() + 1; i <= getCurrentTile().get_PosX() + 2; i++, j++) {

                    if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                        break;
                    }

                    if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j] != t) {
                        return false;
                    }

                    if (board.getMatrix()[i][j] == t) {
                        return true;
                    }

                }

                return false;

            default:
                Unpath();
                return false;

        }

    }

    



@Override
        public boolean CanKill(Piece p) {
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

        if (this.color == Color.WHITE) {
            for (int i = getCurrentTile().get_PosX() + 1; i <= getCurrentTile().get_PosX() + 3; i++) {

                if (i < 0 || i >= 10) {
                    System.out.println("oi");
                    break;
                } else {
                    if (board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece() != null) {
                        System.out.println("io");
                        Kill(board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece());
                    }

                }
            }
        } else {
            for (int i = getCurrentTile().get_PosX() - 1; i >= getCurrentTile().get_PosX() - 3; i--) {

                if (i < 0 || i >= 10) {
                    break;
                } else {
                    if (board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece() != null) {
                        Kill(board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece());
                    }
                }
            }
        }
    }
}
