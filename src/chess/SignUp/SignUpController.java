package chess.SignUp;

import chess.Chess;
import chess.Database.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class SignUpController implements Initializable {

    
    public Text textError;
    public TextField etUser;
    public TextField etname;
    public PasswordField etPass1;
    public PasswordField etPass2;
    
    
    public void SignUpAction(ActionEvent event) throws IOException{
        String user = etUser.getText();
        String pass1 = etPass1.getText();
        String pass2 = etPass2.getText();
        String nome = etname.getText();
        
        if(pass1.equals(pass2)){
            String aux = ConnectDB.readStringFromURL(ConnectDB.Insere + user + "&senha=" + pass1 + "&nome=" + nome);
            if(aux.equals("1")){
                Chess.ChangeStage("Login");
                JOptionPane.showMessageDialog(null, "You are now Signed Up at Hero Chess");
            }
            else if (aux.equals("-1"))textError.setText("Already Signed Up");
            else textError.setText("Something went wrong!");
        }
        else textError.setText("Psswords aren't the same");
        
        
    }
    
    public void BackToLogin(ActionEvent event){
        Chess.ChangeStage("Login");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
