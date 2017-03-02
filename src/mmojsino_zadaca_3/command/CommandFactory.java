/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.command;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.Entry.comparingByValue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import mmojsino_zadaca_3.Oprema;
import mmojsino_zadaca_3.Ronioc;
import mmojsino_zadaca_3.memento.Memento;
import mmojsino_zadaca_3.mvc.Controller;
import mmojsino_zadaca_3.state.MinimalnaOpremaState;
import mmojsino_zadaca_3.state.VisaRazinaOpremaState;

public final class CommandFactory {

    private static String promjenaNacinPridruzivanja = "^N ([a-zA-Z]*)$";
    private static String brisanjeRonioca = "^([a-zA-Z]{3,})$";

    private static String ispisOprema = "^([a-zA-Z]{3,})$";

    private final HashMap<String, Command> commands;
    private String command;
    private Controller con;

    public CommandFactory() {
        commands = new HashMap<>();
    }

    public CommandFactory init(CommandFactory cf, Controller c) {
        cf.addCommand("K_R", () -> komandeRonioci());
        cf.addCommand("K_O", () -> komandeOprema());
        cf.con = c;
        return cf;
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name) {
        if (commands.containsKey(name)) {
            commands.get(name).apply();
        }
    }

    public void listCommands() {
        System.out.println("Enabled commands: " + commands.keySet().stream().collect(Collectors.joining(", ")));
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setController(Controller c) {
        this.con = c;
    }

    public void komandeRonioci() {
        Pattern promjena = Pattern.compile(promjenaNacinPridruzivanja);
        Pattern brisanje = Pattern.compile(brisanjeRonioca);
        Matcher m_promjena = promjena.matcher(this.command);
        Matcher m_brisanje = brisanje.matcher(this.command);

        if (m_promjena.matches()) {
            //postaviti za promjenu nacina pridruzivanja
            Ronioc r = con.getModel().getListaRoniocaOdabrani().stream().filter(x -> x.getImeRonioca().equals(m_promjena.group(1))).findAny().orElse(null);
            if (r != null) {
                if (r.getStanje().toString().contains("Minimalna")) {
                    //postaviti max
                    r.setStanje(new VisaRazinaOpremaState());
                    r.getStanje().setOprema(r, con.getModel().getOpremaList(), con.getUp());
                    con.setPoruka("Nacin pridruzivanja promijenjen za ronioca: " + r.getImeRonioca());
                } else {
                    //postaviti min
                    r.setStanje(new MinimalnaOpremaState());
                    r.getStanje().setOprema(r, con.getModel().getOpremaList(), con.getUp());
                    con.setPoruka("Nacin pridruzivanja promijenjen za ronioca: " + r.getImeRonioca());
                }
            } else {
                System.out.println("Upisani ronioc nije tocan.");
            }
            return;
        } else if (m_brisanje.matches()) {
            Ronioc r = con.getModel().getListaRoniocaOdabrani().stream().filter(x -> x.getImeRonioca().equals(m_brisanje.group(0))).findAny().orElse(null);
            if (r != null) {
                con.getModel().deleteFromOdabrani(r);
                con.setPoruka("Obrisan je ronioc: " + m_brisanje.group(0));
            }
            return;
        }
        switch (this.command) {
            case "P":
                con.setPoruka(con.getModel().preostaliRonioci());
                break;
            case "V":
                Memento m = con.getCr().getMementoList().get(0);
                con.getModel().getStateFromMemento(m);
                con.setPoruka("Vraceno na inicijalno stanje.");
                break;
            case "G":
                con.setPoruka("Nije implementirano");
                break;
            case "D":
                con.setPoruka("Nije implementirano");
                break;
            case "N":
                con.setPoruka("Nastavak.");
                break;
        }
    }

    public void komandeOprema() {
        Pattern ispisOp = Pattern.compile(ispisOprema);
        Matcher ispisOpreme = ispisOp.matcher(this.command);
        String poruka = "";
        if (ispisOpreme.matches()) {
            Ronioc r = con.getModel().getListaRoniocaOdabrani().stream().filter(x -> x.getImeRonioca().equals(ispisOpreme.group(1))).findAny().orElse(null);
            if (r != null) {
                for (Oprema o : r.getOprema()) {
                    poruka += o.getNazivOpreme() + " - ";
                }
                StringBuffer buffer = new StringBuffer(poruka);
                poruka = buffer.reverse().toString().replaceFirst("-", "");
                poruka = new StringBuffer(poruka).reverse().toString();
                con.setPoruka(poruka);
            }
        }
        switch (this.command) {
            case "O":
                Map<String, Integer> counters = con.getModel().getOpremaList()
                        .stream().collect(Collectors.groupingBy(Oprema::getNazivOpreme, Collectors.summingInt(Oprema::getBrojKomada)));
                counters.forEach((k,v)-> {
                    System.out.println("Naziv opreme: " + k + ", preostalo: " + v);
                });
                break;
            case "V":
                con.setPoruka("V");
                break;
            case "Q":
                break;
        }
    }
}
