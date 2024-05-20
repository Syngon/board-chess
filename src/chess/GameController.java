package chess;

import chess.Board.Tile;
import static chess.Chess.HEIGHT;
import static chess.Chess.WIDTH;
import static chess.Chess.board;
import chess.Database.ConnectDB;
import chess.Piece.Piece;
import chess.Piece.Saitama;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

public class GameController {

    private int round;
    private Tile originTile;
    private Tile destinyTile;
    private Tile originSkillTile;
    private Tile destinySkillTile;

    public GameController() {

    }

    public void killFriend() {
        JOptionPane.showMessageDialog(null, "U really wanna kill ur friend?");
    }

    @FXML
    public int getRound() {
        return round;
    }

    @FXML
    public void setRound(int round) {

        this.round = round;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j].getCurrentPiece().getId().equals("Saitama")) {

                    Saitama name = (Saitama) board.getMatrix()[i][j].getCurrentPiece();

                    if (name.getOriginTile() != name.getCurrentTile()) {
                        name.GetBack();
                    }

                }

            }
        }

    }

    @FXML
    public void resetAll() {

        this.destinySkillTile = null;
        this.originSkillTile = null;
        this.originTile = null;
        this.destinyTile = null;

    }

    @FXML
    public Tile getOriginTile() {
        return originTile;
    }

    @FXML
    public void setOriginTile(Tile originTile) {
        this.originTile = originTile;
        this.originTile.getCurrentPiece().Path();
    }

    @FXML
    public Tile getDestinyTile() {
        return destinyTile;
    }

    @FXML
    public void setDestinyTile(Tile destinyTile) throws IOException {

        this.destinyTile = destinyTile;
        this.originTile.getCurrentPiece().Unpath();
        getOriginTile().getCurrentPiece().Move(getDestinyTile());

    }

    @FXML
    public String GetRelativePath(Tile t) {

        if (getOriginTile() == t) {
            return "Nowhere";
        } //North and South
        else if (getOriginTile().get_PosY() == t.get_PosY()) {

            //South
            if (getOriginTile().get_PosX() < t.get_PosX()) {
                return "South";
            } //North
            else if (getOriginTile().get_PosX() > t.get_PosX()) {
                return "North";
            }

        } //West and East
        else if (getOriginTile().get_PosX() == t.get_PosX()) {

            if (getOriginTile().get_PosY() < t.get_PosY()) {
                return "East";
            } else if (getOriginTile().get_PosY() > t.get_PosY()) {
                return "West";
            }

        } //Southeast and Southwest
        else if (getOriginTile().get_PosX() < t.get_PosX()) {

            if (getOriginTile().get_PosY() > t.get_PosY()) {
                return "Southwest";
            } else if (getOriginTile().get_PosY() < t.get_PosY()) {
                return "Southeast";
            }

        } else if (getOriginTile().get_PosX() > t.get_PosX()) {

            if (getOriginTile().get_PosY() > t.get_PosY()) {
                return "Northwest";
            } else if (getOriginTile().get_PosY() < t.get_PosY()) {
                return "Northeast";
            }

        }

        return "Error";

    }

    public Tile getOriginSkillTile() {
        return originSkillTile;
    }

    public void setOriginSkillTile(Tile originSkillTile) {
        this.originSkillTile = originSkillTile;
    }

    public Tile getDestinySkillTile() {
        return destinySkillTile;
    }

    public void setDestinySkillTile(Tile destinySkillTile) {

        this.destinySkillTile = destinySkillTile;
        getOriginSkillTile().getCurrentPiece().Skill(destinySkillTile);

    }

    public boolean canCastSaitama(Color c) {

        int n = 0;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                if (n == 5) {
                    return true;
                }

                if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j].getCurrentPiece().getColor() == c && !board.getMatrix()[i][j].getCurrentPiece().getId().contains("King")) {
                    n++;
                }
            }
        }
        return false;
    }

    public void castSaitama() {
        System.out.println("oi");
        Random random = new Random();
        Tile t = null;

        //JOGADOR BRANCO
        if (canCastSaitama(Color.WHITE) && getRound() % 2 == 0 && getRound() > 8) {

            ArrayList<Piece> pieces = new ArrayList();

            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {

                    if (board.getMatrix()[i][j].getCurrentPiece() != null) {

                        if (board.getMatrix()[i][j].getCurrentPiece().getColor() == Color.WHITE && !board.getMatrix()[i][j].getCurrentPiece().getId().contains("King")) {
                            pieces.add(board.getMatrix()[i][j].getCurrentPiece());
                        }
                    }
                }
            }

            int n = 0;

            while (n != 5) {
                if (pieces.size() < 0) {
                    return;
                }
                int dead = random.nextInt(pieces.size());
                t = pieces.get(dead).getCurrentTile();
                board.getBoardAsGrid().getChildren().remove(pieces.get(dead));
                pieces.get(dead).getCurrentTile().setCurrentPiece(null);
                pieces.get(dead).setCurrentTile(null);
                pieces.remove(dead);
                n++;
            }
            pieces.clear();

            int probability = random.nextInt(101);

            if (probability - getRound() < 25) {

                (new Saitama(Color.WHITE, t)).putPieceOnTile(t);

            }
        } //JOGADOR PRETO
        else if (canCastSaitama(Color.BLACK) && getRound() % 2 != 0 && getRound() > 8) {

            ArrayList<Piece> pieces = new ArrayList();

            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {

                    if (board.getMatrix()[i][j].getCurrentPiece() != null) {

                        if (board.getMatrix()[i][j].getCurrentPiece().getColor() == Color.BLACK && !board.getMatrix()[i][j].getCurrentPiece().getId().contains("King")) {
                            pieces.add(board.getMatrix()[i][j].getCurrentPiece());
                        }
                    }
                }
            }

            int n = 0;

            while (n != 5) {

                if (pieces.size() < 0) {
                    return;
                }
                int dead = random.nextInt(pieces.size());
                t = pieces.get(dead).getCurrentTile();
                board.getBoardAsGrid().getChildren().remove(pieces.get(dead));
                pieces.get(dead).getCurrentTile().setCurrentPiece(null);
                pieces.get(dead).setCurrentTile(null);
                pieces.remove(dead);
                n++;

            }
            pieces.clear();

            int probability = random.nextInt(101);

            if (probability - getRound() < 25) {

                (new Saitama(Color.BLACK, t)).putPieceOnTile(t);

            }
        }
    }

    public boolean endGame(Color c) {

        int n = 0;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {

                if (board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j].getCurrentPiece().getColor() == c && !board.getMatrix()[i][j].getCurrentPiece().getId().contains("King")) {
                    n++;
                }
            }
        }
        return !(n > 0);
    }

    public void finish(int i) throws IOException {
        if (Chess.onlineMode) {

            switch (i) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Empate!");
                    break;
                case 1: {
                    ConnectDB.readStringFromURL(ConnectDB.InserirPartida + Chess.idUser1 + "&login2=" + Chess.idUser2 + "&login3=" + Chess.idUser1);
                    Chess.ChangeStage("Menu");
                    JOptionPane.showMessageDialog(null, "Jogador 1 venceu!");
                    break;
                }
                default: {
                    ConnectDB.readStringFromURL(ConnectDB.InserirPartida + Chess.idUser1 + "&login2=" + Chess.idUser2 + "&login3=" + Chess.idUser2);
                    Chess.ChangeStage("Menu");
                    JOptionPane.showMessageDialog(null, "Jogador 2 venceu!");
                    break;
                }
            }
        } else {
            Chess.ChangeStage("Menu");
        }
    }
}
