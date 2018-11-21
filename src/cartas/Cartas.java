/*
 * Licenciado a Carlos Henrique ;)
 */
package cartas;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author carlospereira
 */
public class Cartas {
    
    protected ArrayList <Carta> cartas = new ArrayList<>();
    protected ArrayList <Carta> shuffleddeck = new ArrayList<>();
    protected ArrayList <Carta> monte = new ArrayList<>();
    protected ArrayList <ArrayList<Carta>> montesDupas = new ArrayList<>();
    protected DataBaseConnect db = new DataBaseConnect();
   
    public void makeCartas(){
        
        try{
            
            ResultSet rs = db.getCartas();
            
            while(rs.next()){
                
                if(rs.getInt("peso") == 0){
                    continue;
                }
                
                cartas.add(new Carta(rs.getString("nome"), rs.getInt("peso"), rs.getString("img")));
                
            }
        
        } catch (SQLException e){
            
            System.out.println(e.getMessage());
            
        }
        
//        cartas.forEach((Carta e) -> {
//            System.out.println(e.nomeCarta + " " + e.pesoCarta);
//        });
        
    }
    
    public void shuffleDeck(){
        
        Random rdn = new Random();
        int index;
        
        while(!cartas.isEmpty()){
            
            index = rdn.nextInt( (int) cartas.size() );
            shuffleddeck.add(cartas.remove(index));
           
        }
//        
//        shuffleddeck.forEach((Carta e) -> {
//            System.out.println(e.nomeCarta);
//        });
//        
   
    }
    
    public void monte(){
        
        for(int i=0; i<12; ++i){
            
            monte.add(shuffleddeck.get(i));
            
        }
        
    }
    
    public ArrayList montesDuplas(){
        
        ArrayList<Carta> temp = new ArrayList<>();
                
        
        for(int i=0; i<4; ++i){
            for(int j=0; j<3; ++j){
                temp.add(monte.remove(0));
            }
            montesDupas.add((ArrayList<Carta>) temp.clone());
            temp.clear();
        }
   
        return montesDupas;
            
    }
    
}
