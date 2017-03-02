/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.memento;

import java.util.ArrayList;
import java.util.List;
import mmojsino_zadaca_3.Ronioc;
import mmojsino_zadaca_3.mvc.Model;

/**
 *
 * @author Mariofil
 */
public class Memento {

    private String state;
    private List<Ronioc> listaRoniocOdabrani;

    public Memento(String state, List<Ronioc> lista2) {
        this.state = state;
        this.listaRoniocOdabrani = lista2;
    }

    public String getState() {
        return state;
    }

    public List<Ronioc> getListaRoniocOdabrani() {
        return listaRoniocOdabrani;
    }

    //prototype
    public static List<Ronioc> roniocBackup(Model m) {
        List<Ronioc> backup = new ArrayList<>();
        for (Ronioc r : m.getListaRoniocaOdabrani()) {
            backup.add(r);
        }
        return backup;
    }

    public static List<Ronioc> roniocBackup(Memento m) {
        List<Ronioc> backup = new ArrayList<>();
        for (Ronioc r : m.getListaRoniocOdabrani()) {
            backup.add(r);
        }
        return backup;
    }

}
