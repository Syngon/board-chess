package chess.LoginArea;

import chess.Chess;
import chess.Login1.Login1Controller;
import chess.Login2.Login2Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LoginAreaController implements Initializable {    

    public Button getBtnMenu() {
        return btnMenu;
    }

    public Button getBtnLogin2() {
        return btnLogin2;
    }
 
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnLogin2;
    
    
    public void OpenLogin1(ActionEvent event){
        Login1Controller.lac = this;
        Chess.ChangeStage("Login1");
    }
    public void OpenLogin2(ActionEvent event){
        Login2Controller.lac = this;
        Chess.ChangeStage("Login2");
    }
    public void Offline(ActionEvent event){
        Chess.ChangeStage("Menu");
        Chess.onlineMode = false;
    }
    
    public void openMenu(ActionEvent event){
        Chess.ChangeStage("Menu");
        Chess.onlineMode = true;
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
}
