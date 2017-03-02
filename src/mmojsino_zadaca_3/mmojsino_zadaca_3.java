/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3;

import java.io.IOException;
import mmojsino_zadaca_3.mvc.Controller;
import mmojsino_zadaca_3.mvc.Model;
import mmojsino_zadaca_3.mvc.View;

/**
 *
 * @author Mario
 */
public class mmojsino_zadaca_3 {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        Model model = new Model();
        View view = new View(args);
        
        Controller controller = new Controller(model,view);
        controller.start();
    }

}
