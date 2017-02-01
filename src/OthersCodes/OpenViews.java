package OthersCodes;

import Main.Game;
import Main.Menu;
import javafx.stage.Stage;

/**
 *
 * @author User
 */

public class OpenViews {

    public static void openMenu(){
        
        Menu menu = new Menu();
        
        try {
         
            menu.start(new Stage());
            
        } catch (Exception e) {
            
        }
        
    }
    
    public static void openGame(){
        
        Game game = new Game();
        
        try {
         
            game.start(new Stage());
            
        } catch (Exception e) {
            
        }
        
    }
    
}
