package truco_mineiro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author carlospereira
 */

public class Truco_Mineiro extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
   
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Truco Mineiro");
        stage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);

        
        
        
    }
    
}
