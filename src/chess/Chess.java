package chess;

import chess.Board.Board;
import chess.Piece.Eren;
import chess.Piece.Goku;
import chess.Piece.King;
import chess.Piece.Luffy;
import chess.Piece.Warp;
import chess.Piece.Pawn;
import chess.Piece.Saitama;
import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chess extends Application {
    
    //Online mode
    public static boolean onlineMode; //True = online --- false = offline
    public static boolean id1On = false;
    public static boolean id2On = false;
    public static int idUser1;
    public static int idUser2;
    
    
    public static Stage stage;
    public static Scene MenuScene, BoardScene, Login1Scene, Login2Scene, SignUpScene, LoginArea;
    public static final int TILE_W = 80;
    public static final int TILE_Y = 80;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static Board board = new Board();
    public static GameController gameController = new GameController();
    
    @Override
    public void start(Stage mainStage) throws Exception {
        
        stage = mainStage;

        Parent login1 = FXMLLoader.load(getClass().getClassLoader().getResource("chess/Login1/Login1.fxml"));
        Login1Scene = new Scene(login1);
        
        Parent login2 = FXMLLoader.load(getClass().getClassLoader().getResource("chess/Login2/Login2.fxml"));
        Login2Scene = new Scene(login2);
        
        Parent signUp = FXMLLoader.load(getClass().getClassLoader().getResource("chess/SignUp/SignUp.fxml"));
        SignUpScene = new Scene(signUp);
        
        Parent loginArea = FXMLLoader.load(getClass().getClassLoader().getResource("chess/LoginArea/LoginArea.fxml"));
        LoginArea = new Scene(loginArea);
        
        Parent menu = FXMLLoader.load(getClass().getClassLoader().getResource("chess/Menu/Menu.fxml"));
        MenuScene = new Scene(menu);
        
        Parent boardRoot = board.getBoardAsParent();
        BoardScene = new Scene(boardRoot);
        
        givePieces();
                
        mainStage.setScene(BoardScene);
        mainStage.setTitle("HERO CHESS");
        mainStage.show();
        
    }
    
    public void givePieces(){
        
        Random random = new Random();
        
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < HEIGHT; j++){
                
                int n = random.nextInt(5);
                
                switch (n) {
                    case 0:
                        new Luffy(Color.WHITE).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 1:
                        new King(Color.WHITE).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 2:
                        new Goku(Color.WHITE).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 3:
                        new Pawn(Color.WHITE).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 4:
                        new Eren(Color.WHITE).putPieceOnTile(board.getMatrix()[i][j]);
                        break;   
                    case 5:
                        new Warp(Color.WHITE).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                        
                    default:
                        break;
                }
                    
            }
        }
        
        for(int i = 8; i < 10; i++){
            for(int j = 0; j < HEIGHT; j++){
                
                int n = random.nextInt(5);
                
                switch (n) {
                    case 0:
                        new Luffy(Color.BLACK).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 1:
                        new King(Color.BLACK).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 2:
                        new Goku(Color.BLACK).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 3:
                        new Saitama(Color.BLACK,board.getMatrix()[i][j] ).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    case 4:
                        new Eren(Color.BLACK).putPieceOnTile(board.getMatrix()[i][j]);
                        break;   
                    case 5:
                        new Warp(Color.BLACK).putPieceOnTile(board.getMatrix()[i][j]);
                        break;
                    default:
                        break;
                }
                    
            }
        }
        
    }
    
    public static void ChangeStage(String s){
        
        switch(s){
            
            case "Menu":
                stage.setX(250);
                stage.setY(100);
                stage.setScene(MenuScene);
                break;
            
            case "Login1":
                stage.setScene(Login1Scene);
                break;  
            case "Login2":
                stage.setScene(Login2Scene);
                break;
            case "LoginArea":
                stage.setScene(LoginArea);
                break;    
            case "Board":
                stage.setScene(BoardScene);
                break;
            case "SignUp":
                stage.setScene(SignUpScene);
                break;
                
        }
     
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
