package Controller;

import Main.Menu;
import OthersCodes.OpenViews;
import com.sun.glass.ui.Cursor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author User
 */

public class MenuController implements Initializable {

    @FXML private ImageView img_exit, img_new;
    
    void action(){
        img_exit.setOnMouseClicked(s -> System.exit(0));
        img_new.setOnMouseClicked(s -> {OpenViews.openGame();
            Menu.getStage().close();
        });
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        action();
    }    
    
}
