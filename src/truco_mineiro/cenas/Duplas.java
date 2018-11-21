/*
 * Licenciado a Carlos Henrique ;)
 */
package truco_mineiro.cenas;

public class Duplas {
    
    private int placar;
    
    private boolean Truco;
    private boolean Seis;
    private boolean Nove;
    private boolean Doze;
    
    protected Player jogador1;
    protected Player jogador2;
    
    public Duplas(Player j1, Player j2){
        this.jogador1 = j1;
        this.jogador2 = j2;
        this.placar = 0;
    }
    
    protected Duplas(){}
    
    public void setTruco(){
        Truco = true;
    }
    
    public void setSeis(){
        Seis = true;
    }
    
    public void setNove(){
        Nove = true;
    }
    
    public void setDoze(){
        Doze = true;
    }
    
    public void offTruco(){
        Truco = false;
    }
    
    public void offSeis(){
        Seis = false;
    }
    
    public void offNove(){
        Nove = false;
    }
    
    public void offDoze(){
        Doze = false;
    }
    
    public boolean getTruco(){
       return Truco;
    }
    
    public boolean getSeis(){
        return Seis;
    }
    
    public boolean getNove(){
        return Nove;
    }
    
    public boolean getDoze(){
        return Doze;
    }
    
    public int getPontos(){
        return jogador1.getPontos()+jogador2.getPontos();
    }
    
    public int getPlacar(){
        return placar;
    }
    
    public void recebeTento(int tento){
        this.placar+=tento;
    }
    
    public void zerarTruco(){
       
       Truco = false;
       Seis = false;
       Nove = false;
       Doze = false;
       
   }
}

