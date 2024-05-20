package chess.Piece;

import chess.Board.Tile;
import static chess.Chess.board;
import static chess.Chess.gameController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;


public class Luffy extends Piece{
    
    
    public Luffy(Color color) {
        
        super(color);
        
        if(color == Color.WHITE){
            super.setImagePath("/assets/luffy_azul.png");
        }
        
        else{
            super.setImagePath("/assets/luffy.png");
        }
        
        setImage(new Image(this.imagePath));
        
        super.setId("Luffy");
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
        
            case "Southeast": case "Southwest": case "Northwest": case "Northeast":
                return VerifyTile(t);
                
            default:
                return false;
        }
    }
    
    @Override
    public void Skill(Tile t){
    
        int posYM = gameController.getDestinySkillTile().get_PosY();
        int posXM = gameController.getDestinySkillTile().get_PosX();
        
        int posYP = gameController.getOriginTile().get_PosY();
        int posXP = gameController.getOriginTile().get_PosX();
        
        //VERIFICA SE PODE PUXAR NESSA POSICAO
        if((Math.abs( posXP - posXM) > 5 ) || (Math.abs( posYP - posYM) > 5 ) ) JOptionPane.showMessageDialog(null, "U cant use there");
        else{
            
            //VERIFICA SE NAO TEM PECA
            if(gameController.getDestinySkillTile() == null) JOptionPane.showMessageDialog(null, "No piece there");
            else{
                
                String direction = gameController.GetRelativePath(gameController.getDestinySkillTile());
                Piece peca = gameController.getDestinySkillTile().getCurrentPiece();
                
                switch(direction){
                
                    case "North":
                       if(getPos(-1, 0).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(-1, 0) );
                       else
                        {
                            if(getPos(-1, 0).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                            peca.putPieceOnTile( getPos(-1, 0) );
                            else
                            {
                                Kill(getPos(-1, 0).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(-1, 0) );
                            } 
                        }
                        System.out.println(this.getId() + " pulling North!");
                        break;
                        
                    case "South":
                        if(getPos(1, 0).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(1, 0) );
                        else
                        {
                           
                            if(getPos(1, 0).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(1, 0).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(1, 0) );
                            }
                        }
                        System.out.println(this.getId() + " pulling South!");
                        break;

                    case "East":
                        if(getPos(0, 1).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(0, 1) );
                        else
                        {
                            if(getPos(0, 1).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(0, 1).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(0, 1) );
                            }
                        }
                        System.out.println(this.getId() + " pulling East!");

                        break;

                    case "West":
                        if(getPos(0, -1).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(0, -1) );
                        else
                        {
                            if(getPos(0, -1).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(0, -1).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(0, -1) );
                            }
                        }
                        System.out.println(this.getId() + " pulling West!");
                        break;

                    case "Northeast":
                        if(getPos(-1, 1).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(-1, 1) );
                        else
                        {
                            if(getPos(-1, 1).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(-1, 1).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(-1, 1) );
                            }
                        }   
                        System.out.println(this.getId() + " pulling Northeast!");
                        break;

                    case "Northwest":
                        if(getPos(-1, -1).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(-1, -1) );
                        else
                        {
                            if(getPos(-1, -1).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(-1, -1).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(-1, -1) );
                            }
                        }
                        System.out.println(this.getId() + " pulling Northwest!");
                        break;

                    case "Southeast":
                        if(getPos(1, 1).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(1, 1) );
                        else
                        {
                            if(getPos(1, 1).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(1, 1).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(1, 1) );
                            }
                        }
                        System.out.println(this.getId() + " pulling Southeast!");
                        break;

                    case "Southwest":
                        if(getPos(1, -1).getCurrentPiece() == null)
                            peca.putPieceOnTile( getPos(1, -1) );
                        else
                        {
                            if(getPos(1, -1).getCurrentPiece().getCurrentTile().getCurrentPiece().getId().compareTo("Saitama") == 0)
                                JOptionPane.showMessageDialog(null, "U cant pull him, there's a saitama in front of u");
                            else{
                                Kill(getPos(1, -1).getCurrentPiece());
                                System.out.println("Voce matou " + peca);
                                peca.putPieceOnTile( getPos(1, -1) );
                            }
                        }
                        System.out.println(this.getId() + " pulling Southwest!");
                        break;
                }
            }
        }
        Unpath();
        System.out.println("Skill Activated");
        gameController.resetAll();
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
    
    public Tile getPos(int i, int j){
        return board.getMatrix() [currentTile.get_PosX() + i ] [currentTile.get_PosY() + j ];
    }
    
   
    
    
}