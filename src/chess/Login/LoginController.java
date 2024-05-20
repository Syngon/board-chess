package chess.Login;

import chess.Chess;
import chess.Database.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    public TextField etUser;
    public PasswordField etPass;
    public Text TextError;
    
    public void LoginAction(ActionEvent event) throws IOException{
        
        String user = etUser.getText();
        String pass = etPass.getText();
        
        String aux = ConnectDB.readStringFromURL(ConnectDB.Login + user + "&senha=" + pass);
        if(aux.equals("0")) TextError.setText("Something went wrong!");
        else {
            Chess.ChangeStage("Scene");
            JOptionPane.showMessageDialog(null, "You are logged at Hero Chess");
        }
        
    }
    
    public void OpenSignUp(ActionEvent event) throws IOException{
        
        Chess.ChangeStage("SignUp");
        TextError.setText("");
        
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
