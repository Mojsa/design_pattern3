/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.mvc;

import java.util.ArrayList;
import java.util.List;
import mmojsino_zadaca_3.Oprema;
import mmojsino_zadaca_3.Ronioc;
import mmojsino_zadaca_3.memento.Memento;

/**
 *
 * @author Mariofil
 */
public class Model {

    private List<Ronioc> listaRonioca = new ArrayList<>(); //pocetna lista ronioca iz datoteke ronioci
    private List<Ronioc> listaRoniocaOdabrani = new ArrayList<>();
    private List<Oprema> opremaList = new ArrayList<>();

    public void addRonioc(Ronioc r) {
        this.listaRonioca.add(r);
    }

    public void setListaRoniocaOdabrani(List<Ronioc> listaRoniocaOdabrani) {
        this.listaRoniocaOdabrani = listaRoniocaOdabrani;
    }

    public List<Oprema> getOpremaList() {
        return opremaList;
    }

    public void setOpremaList(List<Oprema> opremaList) {
        this.opremaList = opremaList;
    }

    public List<Ronioc> getListaRonioca() {
        return listaRonioca;
    }

    public List<Ronioc> getListaRoniocaOdabrani() {
        return listaRoniocaOdabrani;
    }

    public void addRoniocOdabrani(Ronioc odabrani) {
        if (!listaRoniocaOdabrani.contains(odabrani)) {
            this.listaRoniocaOdabrani.add(odabrani);
        }
    }

    public void deleteFromOdabrani(Ronioc r) {
        listaRoniocaOdabrani.remove(r);
        /* listaRoniocaOdabrani.removeIf((Ronioc r) -> {
            return r.getImeRonioca().equalsIgnoreCase(name);
        });*/
    }

    public void dodajSpecijalnost(Ronioc r, String s) {
        for (Ronioc ron : listaRonioca) {
            if (ron.equals(r)) {
                ron.dodajSpecijalnost(s);
            }
        }
    }

    public void dodajOpremu(Ronioc r, Oprema o) {
        for (Ronioc ron : listaRonioca) {
            if (ron.equals(r)) {
                ron.dodajOpremu(o);
            }
        }
    }

    public String preostaliRonioci() {
        String poruka = "";
        for (Ronioc r : listaRoniocaOdabrani) {
            poruka += r.getImeRonioca();
            poruka += ",";
        }
        StringBuffer buffer = new StringBuffer(poruka);
        poruka = buffer.reverse().toString().replaceFirst(",", "");
        poruka = new StringBuffer(poruka).reverse().toString();
        return poruka;
    }

    public Memento saveToMemento(String state, List<Ronioc> backup) {
        return new Memento(state, backup);
    }

    public void getStateFromMemento(Memento m) {
        setListaRoniocaOdabrani(Memento.roniocBackup(m));
    }

}
