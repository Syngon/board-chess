package chess.Board;

import static chess.Chess.HEIGHT;
import static chess.Chess.TILE_W;
import static chess.Chess.TILE_Y;
import static chess.Chess.WIDTH;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public final class Board {
    
    private final Tile[][] tilesMatrix;
    private final GridPane board = new GridPane();
    
     public Board(){
        
        this.tilesMatrix = new Tile[WIDTH][HEIGHT];
        
        setMatrix();
        createBoard();
        
    }
    
    private void setMatrix(){
        
        for(int r = 0; r < WIDTH; r++){
            
            for(int c = 0; c < HEIGHT; c++){
                
                if(r%2 == 0){
                    
                    if(c%2 == 0)
                    this.tilesMatrix[r][c] = new Tile(Color.ANTIQUEWHITE, r, c);
                
                else
                    this.tilesMatrix[r][c] = new Tile(Color.DARKGREEN, r, c);
                    
                }
                
                else{
                    
                    if(c%2 == 0)
                    this.tilesMatrix[r][c] = new Tile(Color.DARKGREEN, r, c);
                
                    else
                    this.tilesMatrix[r][c] = new Tile(Color.ANTIQUEWHITE, r, c);
                    
                }
                
            }
        }
        
    }
    
    public void createBoard(){
        
        this.board.setPrefSize(WIDTH*TILE_W, HEIGHT*TILE_Y);
        
        for(int r = 0; r < WIDTH; r++)
            for(int c = 0; c < HEIGHT; c++){              
              
                this.board.add(getMatrix()[r][c], c, r);
                getMatrix()[r][c].relocate(r*TILE_W, c*TILE_Y);
                
            }
        
    }
    
    public Parent getBoardAsParent(){
        return this.board;
    }
    
    public GridPane getBoardAsGrid(){
        return this.board;
    }
    
    public Tile[][] getMatrix(){
        return this.tilesMatrix;   
    }
    
}
