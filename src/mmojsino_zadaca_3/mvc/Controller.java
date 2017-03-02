/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.mvc;

import com.google.common.collect.ListMultimap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import mmojsino_zadaca_3.FileManager;
import mmojsino_zadaca_3.Ronioc;
import mmojsino_zadaca_3.UronPodaci;
import mmojsino_zadaca_3.command.CommandFactory;
import mmojsino_zadaca_3.memento.CareTaker;
import mmojsino_zadaca_3.memento.Memento;
import static mmojsino_zadaca_3.mvc.View.ANSI_ESC;

/**
 *
 * @author Mariofil
 */
//view and model separated
public class Controller {

    private static String workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();

    private Model model;
    private View view;
    private int brojRedakaTemp = 0;
    private int brojRedaka = 0;
    public static final String VT_CLEAR_SCREEN = "\033[2J";
    /* Clear screen */
    public static final String VT_CLEAR_LINE = "\033[2K";

    private CareTaker cr = new CareTaker();
    private CommandFactory cf = new CommandFactory();
    private UronPodaci up;
    private String poruka = "";

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public CareTaker getCr() {
        return cr;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Model getModel() {
        return model;
    }

    public UronPodaci getUp() {
        return up;
    }

    public void start() throws IOException {
        String[] args = view.getArguments();
        if (provjeraArgumenata(args).contains("NOT")) {
            System.out.println("Argumenti nisu valjani!");
            return;
        }
        FileManager fm = new FileManager();
        fm.openFile(workingDirectory + File.separator + args[3], model);
        ListMultimap tm = fm.procitajSpecijalnosti(workingDirectory + File.separator + args[4]);
        for (Ronioc r : model.getListaRonioca()) {
            for (Object ime : tm.keySet()) {
                List<String> spec = (List<String>) tm.get(ime.toString());
                if (ime.toString().equals(r.getImeRonioca())) {
                    for (String s : spec) {
                        model.dodajSpecijalnost(r, s);
                    }
                }
            }
        }
        int tempVode = Integer.parseInt(args[7]);
        int dubina = Integer.parseInt(args[6]);
        int noc = Integer.parseInt(args[8]);
        int snimanja = Integer.parseInt(args[9]);
        up = new UronPodaci(dubina, noc, snimanja, tempVode);
        for (Ronioc r : model.getListaRonioca()) {
            //provjeriti kakvi su uvjeti, te ako spada, dodati u listaodabrani
            if (tempVode <= 20 && noc == 1) { //potrebno odijelo, samo oni sa suhim odjielom i nocno
                for (String s : r.getSpecijalnost()) {
                    if (s.contains("odijelo") && s.contains("ronjenje")) {
                        model.addRoniocOdabrani(r);
                    }
                    if (snimanja > 0 && s.contains("fotograf")) {
                        model.addRoniocOdabrani(r);
                        snimanja--;
                    }
                }
            } else if (tempVode <= 20 && noc == 0) { //potrebno odijelo, nije noc
                for (String s : r.getSpecijalnost()) {
                    if (s.contains("odijelo")) {
                        model.addRoniocOdabrani(r);
                    }
                    if (snimanja > 0 && s.contains("fotograf")) {
                        model.addRoniocOdabrani(r);
                        snimanja--;
                    }
                }

            } else if (noc == 0) { //temp ne treba odijelo, noc nije
                for (String s : r.getSpecijalnost()) {
                    if (snimanja > 0 && s.contains("fotograf")) {
                        model.addRoniocOdabrani(r);
                        snimanja--;
                    }
                }
            } else if (noc == 1) { //provjera uvjeta
                for (String s : r.getSpecijalnost()) {
                    if (s.contains("ronjenje")) {
                        model.addRoniocOdabrani(r);
                    }
                    if (snimanja > 0 && s.contains("fotograf")) {
                        model.addRoniocOdabrani(r);
                        snimanja--;
                    }
                }
            }
        }
        List<Ronioc> backup = Memento.roniocBackup(model);
        cr.add(model.saveToMemento("Inicijalni", backup));
        view.postaviRegiju(brojRedaka);
        brojRedakaTemp = brojRedaka - 1;
        System.out.print(VT_CLEAR_SCREEN);
        System.out.print(ANSI_ESC + brojRedaka + ";" + 1 + "H");
        model.setOpremaList(fm.procitajOpremu(workingDirectory + File.separator + args[5]));
        cf = cf.init(cf, this);
        unosKomandiRonioc();
        if (poruka.contains("Nastavak")) {
            //dalje za opremu
            unosKomandiOprema();
        } else {
            return;
        }
    }

    public String provjeraArgumenata(String[] args) {
        String workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        String poruka = "";

        if (args.length == 10) {
            if (Integer.parseInt(args[0]) >= 24 && Integer.parseInt(args[0]) <= 40) {
                brojRedaka = Integer.parseInt(args[0]);
                brojRedakaTemp = brojRedaka - 1;
            } else {
                poruka = "NOT OK;";
            }
            if (Integer.parseInt(args[1]) >= 80 && Integer.parseInt(args[1]) <= 160) {
            } else {
                poruka = "NOT OK;";
            }
            if (Integer.parseInt(args[2]) >= brojRedaka && Integer.parseInt(args[2]) <= 400) {
            } else {
                poruka = "NOT OK;";
            }
            poruka += provjeriDatoteku(workingDirectory + File.separator + args[3]);
            poruka += provjeriDatoteku(workingDirectory + File.separator + args[4]);
            poruka += provjeriDatoteku(workingDirectory + File.separator + args[5]);
            //provjera dubine
            if (Integer.parseInt(args[6]) >= 5 && Integer.parseInt(args[6]) <= 40) {
            } else {
                poruka = "NOT OK;";
            }
            //provjera temp. vode
            if (Integer.parseInt(args[7]) >= 0 && Integer.parseInt(args[7]) <= 35) {
            } else {
                poruka = "NOT OK;";
            }
            //noc
            if (Integer.parseInt(args[8]) >= 0 && Integer.parseInt(args[8]) <= 1) {
            } else {
                poruka = "NOT OK;";
            }
            poruka = "OK;";
            //args[9] broj potrebnih snimanja
        } else {
            poruka = "NOT OK;";
        }
        return poruka;
    }

    public String provjeriDatoteku(String path) {
        Path filePath = Paths.get(path);
        String poruka = "";
        if (!filePath.toFile().exists()) {
            poruka += "Datoteka na" + path + " ne postoji!";
        }
        return poruka;
    }

    public void updateView(String msg) {
        view.pomakni(brojRedaka);
        view.ispisi(msg, brojRedakaTemp);
        view.pomakni(brojRedaka);
        brojRedakaTemp--;
    }

    private void unosKomandiRonioc() {
        for (Ronioc r : model.getListaRoniocaOdabrani()) {
            r.getStanje().setOprema(r, model.getOpremaList(), up);
        }
        Scanner sc = new Scanner(System.in);
        String unos = "";
        do {
            System.out.print(VT_CLEAR_LINE);
            System.out.print("Enter command ronioc: ");
            unos = sc.nextLine();
            cf.setCommand(unos);
            cf.executeCommand("K_R");
            updateView(poruka);
        } while (!unos.equalsIgnoreCase("Q") && !unos.equalsIgnoreCase("N"));
    }

    private void unosKomandiOprema() {
        Scanner sc = new Scanner(System.in);
        String unos = "";
        do {
            System.out.print(VT_CLEAR_LINE);
            System.out.print("Enter command oprema: ");
            unos = sc.nextLine();
            cf.setCommand(unos);
            cf.executeCommand("K_O");
            updateView(poruka);
            if (poruka.equals("V")) {
                unosKomandiRonioc();
            }
        } while (!unos.equalsIgnoreCase("Q"));
    }

}
