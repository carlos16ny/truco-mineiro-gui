
package truco_mineiro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author carlospereira
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML Button btnplay;
    Stage window = new Stage();
    @FXML AnchorPane pane;
    
    
    
    @FXML
    public void GoLoginPage() throws IOException {
        
        Parent loginView = FXMLLoader.load(getClass().getResource("cenas/login.fxml"));
        Scene loginViewScene = new Scene(loginView);
        
        window = (Stage) pane.getScene().getWindow();
        window.setScene(loginViewScene);
        window.show();

    }
    
    public void makeFadeOut(ActionEvent e) throws Exception{
        
        FadeTransition fadeout = new FadeTransition();
        fadeout.setDuration(Duration.millis(500));
        fadeout.setNode(pane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.setOnFinished((ActionEvent event) -> {
            try {
                GoLoginPage();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fadeout.play();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
