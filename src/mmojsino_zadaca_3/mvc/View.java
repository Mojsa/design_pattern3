/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.mvc;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mariofil
 */
//ispis podataka o roniocima u VT100
public class View {
    //metoda za ispis u VT100

    private String[] arguments;
    public static final String ANSI_ESC = "\033[";

    public View(String[] arguments) {
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void postaviRegiju(int br) {
        System.out.print(ANSI_ESC + (br - 1) + ";" + 1 + "r");
        System.out.print(ANSI_ESC + br + ";" + 1 + "H");
    }

    public void ispisi(String unos, int brTemp) {
        System.out.print(ANSI_ESC + brTemp + ";" + 1 + "H");
        System.out.print(unos);
    }

    public void pomakni(int br) {
        System.out.print(ANSI_ESC + br + ";" + 1 + "H");
    }

}
