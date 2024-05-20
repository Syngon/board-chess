package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import static chess.Chess.gameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Saitama extends Piece{
    
    private Tile originTile;
    
    public Saitama(Color color, Tile t) {
        
        super(color);
            
        if(color == Color.WHITE){
            super.setImagePath("/assets/saitama_azul.png");
        }
        
        else{
            super.setImagePath("/assets/saitama.png");
        }
        
        setImage(new Image(this.imagePath));
        
        super.setId("Saitama");
        
        this.originTile = t;
                
    }
    
    @Override
    public boolean CanMove(Tile t){
        
        if(t.getCurrentPiece() != null){
            if(t.getCurrentPiece().getColor() == getColor()){
                return false;
            }
        }
        
        String direction = gameController.GetRelativePath(t);
        
        switch(direction){
            
            case "North":               
   
                for(int i = getCurrentTile().get_PosX() - 1; i >= t.get_PosX(); i--){
                    
                    if(i < 0 || i >= 10){
                        break;
                    }
                    
                    if(board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece() != null && board.getMatrix()[i][getCurrentTile().get_PosY()] != t){ 
                        return false;
                    }
                    
                    if(board.getMatrix()[i][getCurrentTile().get_PosY()] == t){
                        return true;
                    }
                    
                }
                
                return false;
                
            case "South":
                
                for(int i = getCurrentTile().get_PosX() + 1; i <= t.get_PosX(); i++){
                    
                    if(i < 0 || i >=  10){
                        break;
                    }
                    
                    if(board.getMatrix()[i][getCurrentTile().get_PosY()].getCurrentPiece() != null && board.getMatrix()[i][getCurrentTile().get_PosY()] != t){ 
                        gameController.resetAll();
                        return false;
                    }
                    
                    if(board.getMatrix()[i][getCurrentTile().get_PosY()] == t){
                        return true;
                    }
                    
                }
                
                return false;
            
            case "West":
                
                for(int i = getCurrentTile().get_PosY() - 1; i >= t.get_PosY(); i--){
                    
                    if(i < 0 || i >= 10){
                        break;
                    }
                    
                    if(board.getMatrix()[getCurrentTile().get_PosX()][i].getCurrentPiece() != null && board.getMatrix()[getCurrentTile().get_PosY()][i] != t){ 
                        gameController.resetAll();
                        return false;
                    }
                    
                    if(board.getMatrix()[getCurrentTile().get_PosX()][i] == t){
                        return true;
                    }
                    
                }
                
                return false;
                
            case "East":
                
                for(int i = getCurrentTile().get_PosY() + 1; i <= t.get_PosY(); i++){
                    
                    if(i < 0 || i >= 10){
                        break;
                    }
                    
                    if(board.getMatrix()[getCurrentTile().get_PosX()][i].getCurrentPiece() != null && board.getMatrix()[getCurrentTile().get_PosY()][i] != t){ 
                        return false;
                    }
                    
                    if(board.getMatrix()[getCurrentTile().get_PosX()][i] == t){
                        return true;
                    }
                    
                }
                
                return false;
                
            case "Northwest":
                
                for(int i = getCurrentTile().get_PosX() - 1, j = getCurrentTile().get_PosY() - 1; i >= t.get_PosX(); i--, j--){
                    
                    if(i < 0 || i >= 10 || j < 0 || j >= 10){
                        break;
                    }
                    
                    if(board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j] != t){ 
                        return false;
                    }
                    
                    if(board.getMatrix()[i][j] == t){
                        return true;
                    }
                    
                }
                
                return false;
                
            case "Northeast":
                
                for(int i = getCurrentTile().get_PosX() - 1, j = getCurrentTile().get_PosY() + 1; i >= t.get_PosX(); i--, j++){
                    
                    if(i < 0 || i >= 10 || j < 0 || j >= 10){
                        break;
                    }
                    
                    if(board.getMatrix()[i][j].getCurrentPiece() != null && board.getMatrix()[i][j] != t){ 
                        return false;
                    }
                    
                    if(board.getMatrix()[i][j] == t){
                        return true;
                    }
                    
                }
                
                return false;
                
            case "Southwest":

                for (int i = getCurrentTile().get_PosX() + 1, j = getCurrentTile().get_PosY() - 1; i <= t.get_PosX(); i++, j--) {

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

                for (int i = getCurrentTile().get_PosX() + 1, j = getCurrentTile().get_PosY() + 1; i <= t.get_PosX(); i++, j++) {

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
    
    public void GetBack(){
        
        if(this.color == Color.BLACK){
            
            if(gameController.getRound() % 2 != 0){
                if(getOriginTile().getCurrentPiece() != null){
                    Kill(getOriginTile().getCurrentPiece());
                }
               setCurrentTile(getOriginTile());
               super.putPieceOnTile(getOriginTile());
            }
            
        }
        
        else{
            if(gameController.getRound() % 2 == 0){
                if(getOriginTile().getCurrentPiece() != null){
                    Kill(getOriginTile().getCurrentPiece());
                }
               setCurrentTile(getOriginTile());
               super.putPieceOnTile(getOriginTile()); 
            }
        }
        
    }
    
    @Override
    public boolean VerifyTile(Tile t){
        //TODO
        return false;
    }
    
    
    @Override
    public void Skill(Tile t){
        //TODO
        System.out.println("Skill Activated!");
        gameController.resetAll();
        
    }
    
    @Override
    public boolean CanKill(Piece p){
        return !(p.getId().compareTo("King") == 0 || p.getColor() == getColor());
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

    public Tile getOriginTile() {
        return originTile;
    }

    public void setOriginTile(Tile originTile) {
        this.originTile = originTile;
    }
    
}
