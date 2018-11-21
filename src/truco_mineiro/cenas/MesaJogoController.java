/*
 * Licenciado a Carlos Henrique ;)
 */
package truco_mineiro.cenas;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import cartas.Carta;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 * @author carlospereira
 */

public class MesaJogoController implements Initializable{
    
    ArrayList<Player> players = new ArrayList<>();;
    ArrayList<ArrayList<Carta>> deckReady = new ArrayList<>();
    ArrayList<ArrayList<ImageView>> imagesViews = new ArrayList<>();
    ArrayList<ImageView> monteMesaImage = new ArrayList<>();
    ArrayList<Carta> monteMesaCartas = new ArrayList<>();
    ////////////////////////////////////////////////////////////////
    //Botoes do truco
    @FXML RadioButton Fechada;
    @FXML Button Truco;
    @FXML Button Seis;
    @FXML Button Nove;
    @FXML Button Doze;
    @FXML Button Aceitar;
    @FXML Button Correr;
    
    @FXML Label pontos1;
    @FXML Label pontos2;
    
   /***********Distribuicao de elementos para jogadores*********/
   //Player1
    @FXML Label pl1;
    @FXML ImageView card1_1;
    @FXML ImageView card1_2;
    @FXML ImageView card1_3;
    //Player2
    @FXML Label pl2;
    @FXML ImageView card2_1;
    @FXML ImageView card2_2;
    @FXML ImageView card2_3;
    //Player3
    @FXML Label pl3;
    @FXML ImageView card3_1;
    @FXML ImageView card3_2;
    @FXML ImageView card3_3;
    //Player4
    @FXML Label pl4;
    @FXML ImageView card4_1;
    @FXML ImageView card4_2;
    @FXML ImageView card4_3;
    
    //Mesa Image View
    @FXML ImageView tableCard1;
    @FXML ImageView tableCard2;
    @FXML ImageView tableCard3;
    @FXML ImageView tableCard4;
    
    
    
    /**
     * @param plrs*********************************************************/
    public void initNames(ArrayList<Player> plrs){
       
        players = plrs;
        
        pl1.setText(players.get(0).getName().toUpperCase());
        pl2.setText(players.get(1).getName().toUpperCase());
        pl3.setText(players.get(2).getName().toUpperCase());
        pl4.setText(players.get(3).getName().toUpperCase());
        
        ArrayList<ImageView> aux = new ArrayList<>();
        
        aux.add(card1_1);
        aux.add(card1_2);
        aux.add(card1_3);
        imagesViews.add((ArrayList<ImageView>) aux.clone());
        aux.clear();
        
        aux.add(card2_1);
        aux.add(card2_2);
        aux.add(card2_3);
        imagesViews.add((ArrayList<ImageView>) aux.clone());
        aux.clear();
        
        aux.add(card3_1);
        aux.add(card3_2);
        aux.add(card3_3);
        imagesViews.add((ArrayList<ImageView>) aux.clone());
        aux.clear();
        
        aux.add(card4_1);
        aux.add(card4_2);
        aux.add(card4_3);
        imagesViews.add((ArrayList<ImageView>) aux.clone());
        aux.clear();
       
        monteMesaImage.add(tableCard1);
        monteMesaImage.add(tableCard2);
        monteMesaImage.add(tableCard3);
        monteMesaImage.add(tableCard4);
        
        this.runPartida();
    }
      
    
    public void runPartida(){

        Truco rodada = new Truco(players, imagesViews, monteMesaImage);
        rodada.setButtons(Fechada, Truco, Seis, Nove, Doze, Aceitar, Correr);
        rodada.setLabels(pontos1, pontos2);
        rodada.execute();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
