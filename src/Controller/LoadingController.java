package Controller;

import Main.Loading;
import OthersCodes.OpenViews;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author User
 */
public class LoadingController implements Initializable {
    
    @FXML private ProgressBar pb_loading;
    
    Task task;
    
    void loading(){
        pb_loading.setProgress(0);
        task = Progress();

        pb_loading.progressProperty().unbind();
        pb_loading.progressProperty().bind(task.progressProperty());
        
        new Thread(task).start();
        
    }
    
    public Task Progress() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 200; i++) {
                    Thread.sleep(250);
                    updateProgress(i, 200);
                }
                System.out.println("Acabou");
                return true;
            }

            @Override
            protected void succeeded() {
                OpenViews.openMenu();
                Loading.getStage().close();
            }
            
        };
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loading();
    }    
    
}
