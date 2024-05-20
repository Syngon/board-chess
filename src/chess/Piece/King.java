package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import static chess.Chess.gameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class King extends Piece{
  
    
    public King(Color color) {
        
        super(color);
        
        if(color == Color.WHITE){
            super.setImagePath("/assets/rei_azul.png");
        }
        
        else{
            super.setImagePath("/assets/rei_vermelho.png");
        }
        
        setImage(new Image(this.imagePath));
        
        super.setId("King");
        
    }
    
    @Override
    public boolean VerifyTile(Tile t){
       
        int posYM = t.get_PosY();
        int posXM = t.get_PosX();
        int posYP = getCurrentTile().get_PosY();
        int posXP = getCurrentTile().get_PosX();
        
        return (Math.abs(posYM - posYP) <= 1 && Math.abs(posXM - posXP) <= 1);
    }
    
    @Override
    public boolean CanMove(Tile t){
        
        String direction = gameController.GetRelativePath(t);
        
        switch (direction) { 
            default:
                return VerifyTile(t);
                
        }
    }
    
    @Override
    public boolean CanKill(Piece p){
        return !(p.getColor() == getColor());
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
    
    @Override
    public void Skill(Tile t){
        gameController.castSaitama();
    
    }
    
}
