/*
 * Licenciado a Carlos Henrique ;)
 */
package truco_mineiro.cenas;

import cartas.Carta;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author carlospereira
 */
public class Player extends Duplas{
    
    private String nomePlayer;
    private ArrayList<Carta> maoPlayer;
    private ArrayList<ImageView> imageCartas;
    private int indexJogador;
    protected Label nomePosition;
    private String senha;
    int pontos;
    

    public Player(String nome, String pass) {
        this.nomePlayer = nome;
        this.senha = pass;
    }
    
    public Player(){
        
    }

    public String getName(){
        return this.nomePlayer;
    }
    
    public void setImagesView(ArrayList<ImageView> images){
        this.imageCartas = images;
    }
    
    public void setMaoRodada(ArrayList<Carta> card){
        this.maoPlayer = card;
    }
    
    public void setIndexJogador(int i){
        this.indexJogador = i;
    }
    
    public void setLabelPosition(Label lab){
        this.nomePosition = lab;
    }
    
    public ArrayList<ImageView> getImagesView(){
        return this.imageCartas;
    }
    
    public ArrayList<Carta> getCartas(){
        return this.maoPlayer;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    public int getIndex(){
        return this.indexJogador;
    }
    
    public void setPontos(){
        pontos++;
    }
    
    public void zeraPontos(){
        pontos = 0;
    }
    
    public void correram(){
        pontos+=2;
    }
    
    @Override
    public int getPontos(){
        return pontos;
    }
    
    public void mostrarCartas(){
        for(int i=0; i<maoPlayer.size(); ++i){
            this.imageCartas.get(i).setImage(maoPlayer.get(i).imgCarta);
        } 
    }
    
    public void esconderCartas(){
 
        for(int i=0; i<maoPlayer.size(); ++i){
            this.imageCartas.get(i).setImage(new Image("images/cartas/background-carta.png"));
        }
    }

}
