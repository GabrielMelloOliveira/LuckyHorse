package Controller;

import Main.Game;
import OthersCodes.OpenViews;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class GameController implements Initializable {

    @FXML private ImageView img_exit, img_play_again;
    
    @FXML private ProgressBar pb_loading;
    
    @FXML private Label lb_money, lb_vencedor, lb_segundo_lugar;
    
    private BigDecimal money;
    
    private int number_horse;
    
    private int vencedor, segundo_lugar;
    
    private HashMap map = new HashMap();
    
    void init_map(){
        map.put(1, "Perola");
        map.put(2, "Pe de Pano");
        map.put(3, "Faisca");
        map.put(4, "Topazio");
        map.put(5, "ventania");
        map.put(6, "Corone");
        map.put(7, "Barroso");
        map.put(8, "Raio");
        map.put(9, "Zaino");
        map.put(10, "Xodo");
    }
    
    void action(){
        img_exit.setOnMouseClicked(s -> System.exit(0));
        img_play_again.setOnMouseClicked(s -> {Game.getStage().close();
            OpenViews.openGame();
        });
    }
    
    void enter_horse(){
        number_horse = Integer.valueOf(JOptionPane.showInputDialog("Digite um número entre 1 e 10"));
        enter_money();
    }
    
    void enter_money(){
        double d_money = Double.valueOf(JOptionPane.showInputDialog("Digite o valor da aposta!"));
        money = BigDecimal.valueOf(d_money);
        loading();
    }
    
    void play(){
        
        Random gerador = new Random();
        
        vencedor = gerador.nextInt(10) + 1;
        
        segundo_lugar = gerador.nextInt(10) + 1;
        
        while(vencedor == segundo_lugar){
            segundo_lugar = gerador.nextInt(10) + 1;
        }
 
        System.out.println("Aposta: " + money);
        System.out.println("Numero do cavalo: " + number_horse);
        
        System.out.println("Primeiro lugar: " + vencedor);
        System.out.println("Segundo lugar: " + segundo_lugar);
        
        calculo();
    }
    
    void calculo(){
        
        if(number_horse == vencedor){
            
            money = money.multiply(new BigDecimal("3.0"));
            
        }if (number_horse == segundo_lugar) {
            
            money = money.add(money.divide(new BigDecimal("2.0")));
            
        } else {
            
            money = money.subtract(money);
            
        }
        
        visibilidade_FINISH();
        
    }
    
    void visibilidade_INIT(){
        img_play_again.setVisible(false);
        lb_money.setText("");
        lb_vencedor.setText("");
        lb_segundo_lugar.setText("");
    }
    
    void visibilidade_FINISH(){
        pb_loading.setVisible(false);
        img_play_again.setVisible(true);
        lb_money.setText("Você ganhou: R$ " + money.toString());
        lb_vencedor.setText("Vencedor: " + map.get(vencedor) + " - " + String.valueOf(vencedor));
        lb_segundo_lugar.setText("2 Lugar: " + map.get(segundo_lugar) + " - " + String.valueOf(segundo_lugar));
    }
    
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
                for (int i = 0; i < 60; i++) {
                    Thread.sleep(250);
                    updateProgress(i, 60);
                }
                return true;
            }

            @Override
            protected void succeeded() {
                play();
            }
            
        };
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_map();
        visibilidade_INIT();
        action();
        enter_horse();
    }
    
}
