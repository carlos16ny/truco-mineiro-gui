
package cartas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnect {
    
    public Connection connect(){
        
        Connection conn = null;
        
        try{
            
            String url = "jdbc:sqlite:src/cartas/cartas.db";
            conn = DriverManager.getConnection(url);
            
//            System.out.println("conectado");
            
        }catch (SQLException e){
            
            System.out.println(e.getMessage());
        }
           
        return conn;
    }
    
    public ResultSet getCartas(){
        
        String query = "SELECT * FROM cartas WHERE 1";
        
        ResultSet rs = null;
        
        try {
            
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            
            
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        
        return rs;
        
    }
    
   
}
