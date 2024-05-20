package chess.Mp3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class Mp3Controller implements Initializable {

    Media media = new Media(getClass().getResource("/assets/song_menu.mp3").toString());
    MediaPlayer player = new MediaPlayer(media);
    
    @FXML
    public Text NowPlaying;
    public Text creditos;
    public ImageView pascoagif;
    
    @FXML
    public void StopMusic(){
        
        NowPlaying.setText("");
        player.stop();
        creditos.setText("");
        pascoagif.setVisible(false);
        
    }
    
    @FXML
    public void pauseMusic(){
        player.pause();
    }
    
    @FXML
    public void playMusic(){        
        player.play();
    }
    
    @FXML
    public void volumeIncrease(){
        
        player.setVolume(player.getVolume() + 1);
        System.out.println(player.getVolume());
        
    }
    
    @FXML
    public void volumeDecrease(){
        
        player.setVolume(player.getVolume() - 1);
        System.out.println(player.getVolume());

    }
    
    
    @FXML
    public void SelectMusic(String name, String pathMp3){
        
        creditos.setText("");
        pascoagif.setVisible(false);
        
        String nome = name;
        String path = pathMp3;
        
        StopMusic();
        NowPlaying.setText(nome);
        
        media = new Media(getClass().getResource(path).toString());
        player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        playMusic();
        
    }
    
    @FXML
    public void menuItemAction(ActionEvent event){
        
        MenuItem btnNumber = ((MenuItem)event.getTarget());
        String idMB = btnNumber.getId();

        switch(idMB){
            case "MB1":
                SelectMusic("Boku no Hero - Just Another Hero", "/assets/song_menu.mp3");
                break;
            case "MB2":
                SelectMusic("Boku no Hero - The Day", "/assets/My Hero Academia - The Day.mp3");
                break;    
            case "MB3":
                SelectMusic("Zelda Ocarina of Time - Lost Woods", "/assets/Zelda Ocarina of Time Music - Lost Woods.mp3");
                break;    
            case "MB4":
                SelectMusic("Naruto - Sadness and Sorrow", "/assets/Naruto - Sadness And Sorrow (Original).mp3");
                break;
            case "MB5":
                SelectMusic("Naruto - Strong and Strike", "/assets/naruto-ost-1-strong-and-strike.mp3");
                break;
            case "MB6":
                SelectMusic("Dragon Ball - Cha La Head Cha La", "/assets/Dragon Ball OST CD5 - Cha-La Head-Cha-La (Instrumental).mp3");
                break;
            case "MB7":
                SelectMusic("Dragon Ball - Dan Dan Kokoro", "/assets/DAN DAN kokoro hikareteku KARAOKE (Instrumentallyrics).mp3");
                break;
            case "MB8":
                SelectMusic("Dragon Ball - We Gotta Power", "/assets/Dragon Ball Z BGM - M1719 Full WE GOTTA POWER (Instrumental).mp3");
                break;
            case "MB9":
                SelectMusic("One Piece - We Are", "/assets/One Piece We Are Instrumental.mp3");
                break;
            case "MB10":
                SelectMusic("One Punch Man - Hero", "/assets/One Punch Man - Opening Full (Instrumental) Off vocals HERO.mp3");
                break;
        }     
    }
    
    public void Secrets(ActionEvent event){
        
        SelectMusic("Atingido o suprassumo da felicidade, Lapa te deve 200 big big", "/assets/pascoa.mp3");
        creditos.setText("CREDITOS:\n\nAlex\nEnzo\nFlavio\nPequeno\nJolisan\nPedro\nFinado Xande S2");
        pascoagif.setVisible(true);
        
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}