package chess.Login1;

import chess.Chess;
import chess.Database.ConnectDB;
import chess.LoginArea.LoginAreaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class Login1Controller implements Initializable {

    public TextField etUser;
    public PasswordField etPass;
    public static LoginAreaController lac;
    
    public void LoginAction(ActionEvent event) throws IOException{
        
        String user = etUser.getText();
        String pass = etPass.getText();
        
        String aux = ConnectDB.readStringFromURL(ConnectDB.Login + user + "&senha=" + pass);
        if(aux.equals("0")) JOptionPane.showMessageDialog(null, "Something went wrong!");
        else {
            Chess.id1On = true;
            Chess.idUser1 = Integer.parseInt(aux);
            lac.getBtnLogin2().setVisible(true);
            Chess.ChangeStage("LoginArea");
            JOptionPane.showMessageDialog(null, "Player 1 logged at Hero Chess");
        }
        
    }
    
    public void OpenSignUp(ActionEvent event) throws IOException{
        
        Chess.ChangeStage("SignUp");
        
    }
    
    public void LoginArea(ActionEvent event){
        Chess.ChangeStage("LoginArea");
    }
    
     @FXML
    public void OpenMenu(ActionEvent event){
        
        Chess.ChangeStage("Menu");      
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
