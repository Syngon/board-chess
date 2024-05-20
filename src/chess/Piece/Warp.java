package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Warp extends Piece {

    public Warp(Color color) {
        super(color);

        if(color == Color.WHITE){
            super.setImagePath("/assets/WarpBlue.png");
        }
        
        else{
            super.setImagePath("/assets/WarpRed.png");
        }
        
        setImage(new Image(this.imagePath));
        
        super.setId("Mirio");
    }

    @Override
    public boolean VerifyTile(Tile t) {

        int posYM = t.get_PosY();
        int posXM = t.get_PosX();
        int posYP = getCurrentTile().get_PosY();
        int posXP = getCurrentTile().get_PosX();

        return (Math.abs(posYM - posYP) <= 1 && Math.abs(posXM - posXP) <= 1);
    }

    public boolean CanMove(Piece p) {
        return true;
    }

    @Override
    public boolean CanKill(Piece p) {
        return p.getColor() != getColor();
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
    public void Skill(Tile t){

        Piece p = t.getCurrentPiece();
        Tile o = getCurrentTile(); //Mirio
        Tile k = t; 

        board.getBoardAsGrid().getChildren().remove(p);
        p.getCurrentTile().setCurrentPiece(null);
        p.setCurrentTile(null);

        try {
            Move(k);
        } catch (IOException ex) {
            Logger.getLogger(Warp.class.getName()).log(Level.SEVERE, null, ex);
        }

        p.putPieceOnTile(o);

    }

}
