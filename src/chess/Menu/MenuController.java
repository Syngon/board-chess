package chess.Menu;

import chess.Chess;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    
    TextField entryLogin1;
    TextField passField1;   
    
    public static String readStringFromURL(String requestURL) throws IOException{
	   
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
	    
            StandardCharsets.UTF_8.toString())){
            
	        scanner.useDelimiter("\\A");
	        return scanner.hasNext() ? scanner.next() : "";
                
	    }
        
    }
    
    
    
    @FXML
    public void SignUpAction(ActionEvent event) throws IOException{
        
        readStringFromURL( "http://localhost/xadrez/GetInfo.php?login=" + 1 + "&senha=" + 5 + "&nome=" + "teste" );

    }
    
    @FXML
    public void OpenMp3(ActionEvent event) {
        
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("chess/Mp3/Mp3.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 560, 380));
            stage.setResizable(false);
            stage.setTitle("Mp3Player");
            stage.show();
            
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    @FXML
    public void SecretButton(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("chess/Menu/JavaNao.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 560, 380));
            stage.setResizable(false);
            stage.setTitle("JAVANAO");
            stage.show();
            
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void OpenBoard(){
        
        Chess.ChangeStage("Board");     
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
