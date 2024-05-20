package chess.Database;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConnectDB {
 
    public static String Login = "http://localhost/xadrez/login.php?login=";                    //login=(user)&senha=(pass)&nome=(name)
    public static String Insere = "http://localhost/xadrez/insere.php?login=";                  //login(user)&senha=(pass)
    public static String InserirPartida = "http://localhost/xadrez/inserirPartida.php?login1="; //login1=(ID)&login2=(ID)&login3=(ID)
    public static String GetPartidas = "http://localhost/xadrez/getPartidas.php?login=";        //login=(ID)
    public static String GetVitorias = "http://localhost/xadrez/getVitorias.php?login=";        //login=(ID)
    
    public static String readStringFromURL(String requestURL) throws IOException{
	    try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
	            StandardCharsets.UTF_8.toString()))
	    {
	        scanner.useDelimiter("\\A");
	        return scanner.hasNext() ? scanner.next() : "";
	    }
    }
    
}
