package cartas;

import javafx.scene.image.Image;

/**
 * @author carlospereira
 */

public class Carta{
    
    private String nomeCarta;
    private int pesoCarta;
    public Image imgCarta;
    
    public Carta(){}
    
    public Carta(String nome, int peso, String img){
        this.imgCarta = new Image(img);
        this.nomeCarta = nome;
        this.pesoCarta = peso;
    }
    
    public String getName(){
        return this.nomeCarta;
    }
    
    public int getPeso(){
        return this.pesoCarta;
    }
    
    
    
}
