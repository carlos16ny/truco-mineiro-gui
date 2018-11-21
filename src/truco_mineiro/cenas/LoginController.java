/*
 * Licenciado a Carlos Henrique ;)
 */
package truco_mineiro.cenas;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author carlospereira
 */
public class LoginController implements Initializable {
    
    public ArrayList<Player> jogadores = new ArrayList<>();
   
    @FXML TextField player1;
    @FXML TextField player2;
    @FXML TextField player3;
    @FXML TextField player4;
    
    @FXML PasswordField p1_pass;
    @FXML PasswordField p2_pass;
    @FXML PasswordField p3_pass;
    @FXML PasswordField p4_pass;
    
    @FXML AnchorPane pane2;

    @FXML
    public void getNomesDuplas(ActionEvent e) throws IOException{
         
        if(player1.getText().equals("") ||
           player2.getText().equals("") ||
           player3.getText().equals("") ||
           player4.getText().equals("") ||
           p1_pass.getText().equals("") ||
           p2_pass.getText().equals("") ||
           p3_pass.getText().equals("") ||
           p4_pass.getText().equals("") ){

            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERRO DOIDÃO");
            a.setHeaderText("Está faltando Jogadores ou Senhas");
            a.setResizable(false);
            a.setContentText("Enquando não houver o nome dos quatro jogadores, a partida não pode se inicializar!");
            a.showAndWait();
            a.setHeight(90.00);
            
        } else{
            jogadores.add(new Player(player1.getText(), p1_pass.getText()));
            jogadores.add(new Player(player2.getText(), p2_pass.getText()));
            jogadores.add(new Player(player3.getText(), p3_pass.getText()));
            jogadores.add(new Player(player4.getText(), p4_pass.getText()));
            
            System.out.println(jogadores.get(0).getName() + " | " + jogadores.get(0).getSenha());
            System.out.println(jogadores.get(1).getName() + " | " + jogadores.get(1).getSenha());
            System.out.println(jogadores.get(2).getName() + " | " + jogadores.get(2).getSenha());
            System.out.println(jogadores.get(3).getName() + " | " + jogadores.get(3).getSenha());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mesajogo.fxml"));
            Parent mesaView = loader.load();
            
            Scene mesaViewScene = new Scene(mesaView);
            
            MesaJogoController controller = loader.getController();
            controller.initNames(jogadores);
    
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(mesaViewScene);
            window.centerOnScreen();
            window.show();
            
//            System.out.println(window.getX() + " || " + window.getY());
            
        }
        
    }
    
    public void makeFadeIn(){
        FadeTransition fadeout = new FadeTransition();
        fadeout.setDuration(Duration.millis(500));
        fadeout.setNode(pane2);
        fadeout.setFromValue(0);
        fadeout.setToValue(1);
        fadeout.play();
        
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeFadeIn();
    }   
    
}
