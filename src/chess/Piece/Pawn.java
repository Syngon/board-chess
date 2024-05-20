package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import static chess.Chess.gameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

public class Pawn extends Piece{
    
    //Verify if this pawn can move 2 tiles, first movement of this pawn
    boolean primeiraJogada = true;
    
    
    public Pawn(Color color) {
        super(color);

        if(color == Color.WHITE){
            super.setImagePath("/assets/peao_azul.png");
        }
        
        else{
            super.setImagePath("/assets/peao_normal.png");
        }
        
        setImage(new Image(this.imagePath));
        
        super.setId("Pawn");
    }
    
    @Override
    public boolean VerifyTile(Tile t){
       
        int posYM = t.get_PosY();
        int posXM = t.get_PosX();
        int posYP = getCurrentTile().get_PosY();
        int posXP = getCurrentTile().get_PosX();
        
        return (Math.abs(posYM - posYP) <= 1 && Math.abs(posXM - posXP) <= 2);
    }
    
    @Override
    public boolean CanMove(Tile t){
        
        String direction = gameController.GetRelativePath(t);
        
        switch (direction) {
        
            case "North":
                return VerifyTile(t);
                
            default:
                return false;
        }
    }
    
    @Override
    public boolean CanKill(Piece p){
        return !(p.getId().compareTo("Saitama") == 0 || p.getColor() == getColor());
    }
    
    @Override
    public boolean Kill(Piece p){
        
       if(CanKill(p)){
           
            board.getBoardAsGrid().getChildren().remove(p);
            p.getCurrentTile().setCurrentPiece(null);
            p.setCurrentTile(null);
            return true;
       }
        
       return false;
       
    }
    
    
}
