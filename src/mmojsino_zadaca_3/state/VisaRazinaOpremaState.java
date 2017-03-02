/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.state;

import java.util.List;
import mmojsino_zadaca_3.Oprema;
import mmojsino_zadaca_3.Ronioc;
import mmojsino_zadaca_3.UronPodaci;

/**
 *
 * @author Mariofil
 */
public class VisaRazinaOpremaState implements State {

    @Override
    public void setOprema(Ronioc r, List<Oprema> oprema, UronPodaci up) {
        r.setStanje(this);
        if (!r.getOprema().isEmpty()) {
            r.getOprema().clear();
        }
        for (String spec : r.getSpecijalnost()) {
            if (spec.contains("Suho odijelo")) {
                Oprema op = oprema.stream().filter(x -> x.getNazivOpreme().matches(".*(neopren|trilaminat).*") && x.getTemp() <= up.getTemp()
                        && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
                Oprema op2 = oprema.stream().filter(x -> x.getNazivOpreme().matches(".*(neopren|trilaminat).*")
                        && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
                if (op != null) {
                    op.setBrojKomada(op.getBrojKomada() - 1);
                    r.dodajOpremu(op);
                    if (op.getTrebaPododijelo() == '+') {
                        //pododijelo
                        Oprema op_pod = oprema.stream().filter(x -> x.getNazivOpreme().contains("Pododijelo za suho odijelo") && x.getTemp() <= up.getTemp()
                                && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
                        if (op_pod != null) {
                            op.setBrojKomada(op_pod.getBrojKomada() - 1);
                            r.dodajOpremu(op_pod);
                        }
                    }
                } else if (op2 != null) {
                    op2.setBrojKomada(op2.getBrojKomada() - 1);
                    r.dodajOpremu(op2);
                    if (op2.getTrebaPododijelo() == '+') {
                        //pododijelo
                        Oprema op_pod = oprema.stream().filter(x -> x.getNazivOpreme().contains("Pododijelo za suho odijelo") && x.getTemp() <= up.getTemp()
                                && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
                        if (op_pod != null) {
                            op_pod.setBrojKomada(op_pod.getBrojKomada() - 1);
                            r.dodajOpremu(op_pod);
                        }
                    }
                } else {
                    //nema dostupnog suhog odijela, staviti minimum mokrog
                    Oprema mokro = oprema.stream().filter(x -> x.getNazivOpreme().matches(".*(Mokro odijelo|Polu suho odijelo).*") && x.getTemp() <= up.getTemp()
                            && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
                    if (mokro != null) {
                        mokro.setBrojKomada(mokro.getBrojKomada() - 1);
                        r.dodajOpremu(mokro);
                        if (mokro.getTrebaKapuljacu() == '+') {
                            Oprema kapuljaca = oprema.stream().filter(x -> x.getNazivOpreme().contains("Kapulj") && x.getTemp() <= up.getTemp()
                                    && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
                            if (kapuljaca != null) {
                                kapuljaca.setBrojKomada(kapuljaca.getBrojKomada() - 1);
                                r.dodajOpremu(kapuljaca);
                            }
                        }
                    }
                }
            } else if (spec.contains("ronjenje")) {
                //nocne stvari
                Oprema racunalo = oprema.stream().filter(x -> x.getNazivOpreme().contains("računalo")
                        && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getBrojKomada(), p2.getBrojKomada())).orElse(null);
                if (racunalo != null) {
                    racunalo.setBrojKomada(racunalo.getBrojKomada() - 1);
                    r.dodajOpremu(racunalo);
                }
                Oprema svjetiljka = oprema.stream().filter(x -> x.getNazivOpreme().contains("svjetiljka")
                        && x.getBrojKomada() > 0).findFirst().orElse(null);
                if (svjetiljka != null) {
                    svjetiljka.setBrojKomada(svjetiljka.getBrojKomada() - 1);
                    r.dodajOpremu(svjetiljka);
                }
            } else if (spec.contains("fotograf")) {
                Oprema fotoaparat = oprema.stream().filter(x -> x.getNazivOpreme().matches(".*(GoPro|fotoaparat).*")
                        && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getBrojKomada(), p2.getBrojKomada())).orElse(null);
                if (fotoaparat != null) {
                    fotoaparat.setBrojKomada(fotoaparat.getBrojKomada() - 1);
                    r.dodajOpremu(fotoaparat);
                }
                Oprema rasvjeta = oprema.stream().filter(x -> x.getNazivOpreme().matches(".*(rasvjeta|video svjetiljka).*")
                        && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getBrojKomada(), p2.getBrojKomada())).orElse(null);
                if (rasvjeta != null) {
                    rasvjeta.setBrojKomada(rasvjeta.getBrojKomada() - 1);
                    r.dodajOpremu(rasvjeta);
                }
            }
        }
        //provjera ima li uopce odijelo: 
        Oprema o = r.getOprema().stream().filter(x -> x.getNazivOpreme().matches(".*(Mokro odijelo|Polu suho odijelo|Suho odijelo).*")).findAny().orElse(null);
        if (o == null) {
            //dodati mokro odijelo
            Oprema mokro = oprema.stream().filter(x -> x.getNazivOpreme().matches(".*(Mokro odijelo|Polu suho odijelo).*") && x.getTemp() <= up.getTemp()
                    && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
            mokro.setBrojKomada(mokro.getBrojKomada() - 1);
            r.dodajOpremu(mokro);
        }
        //stvari za sve ronioce
        o = oprema.stream().filter(x -> x.getNazivOpreme().contains("Maska") && x.getBrojKomada() > 0).findAny().orElse(null);
        o.setBrojKomada(o.getBrojKomada() - 1);
        r.dodajOpremu(o);
        o = oprema.stream().filter(x -> x.getNazivOpreme().contains("Disalica") && x.getBrojKomada() > 0).findAny().orElse(null);
        o.setBrojKomada(o.getBrojKomada() - 1);
        r.dodajOpremu(o);
        o = oprema.stream().filter(x -> x.getNazivOpreme().contains("Peraje") && x.getBrojKomada() > 0).findAny().orElse(null);
        o.setBrojKomada(o.getBrojKomada() - 1);
        r.dodajOpremu(o);
        Oprema rukavice = oprema.stream().filter(x -> x.getNazivOpreme().contains("Rukavice") && x.getTemp() >= up.getTemp() && x.getTemp() <= up.getTemp()
                && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
        if (rukavice != null) {
            rukavice.setBrojKomada(rukavice.getBrojKomada() - 1);
            r.dodajOpremu(rukavice);
        }
        Oprema cizme = oprema.stream().filter(x -> x.getNazivOpreme().contains("izme") && x.getTemp() <= up.getTemp()
                && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
        if (cizme != null) {
            cizme.setBrojKomada(cizme.getBrojKomada() - 1);
            r.dodajOpremu(cizme);
        }
        Oprema regulator = oprema.stream().filter(x -> x.getNazivOpreme().contains("Regulator") && x.getTemp() <= up.getTemp()
                && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getTemp(), p2.getTemp())).orElse(null);
        if (regulator != null) {
            regulator.setBrojKomada(regulator.getBrojKomada() - 1);
            r.dodajOpremu(regulator);
        }
        Oprema boca = oprema.stream().filter(x -> x.getNazivOpreme().contains("Boca za ronjenje")
                && x.getBrojKomada() > 0).min((p1, p2) -> Integer.compare(p1.getBrojKomada(), p2.getBrojKomada())).orElse(null);
        if (boca != null) {
            boca.setBrojKomada(boca.getBrojKomada() - 1);
            r.dodajOpremu(boca);
        }
    }

    @Override
    public String toString() {
        return "Visa razina oprema."; //To change body of generated methods, choose Tools | Templates.
    }

}
