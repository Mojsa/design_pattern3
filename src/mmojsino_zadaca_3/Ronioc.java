/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3;

import java.util.ArrayList;
import java.util.List;
import mmojsino_zadaca_3.agencije.Agency;
import mmojsino_zadaca_3.agencije.AgencyFactory;
import mmojsino_zadaca_3.state.MinimalnaOpremaState;
import mmojsino_zadaca_3.state.State;

/**
 *
 * @author Mario
 */
public class Ronioc {

    //required
    private String imeRonioca;
    private int godinaRodjenja;

    //optional
    private String agencija;
    private String agencijaNorm;
    private String rang;
    private String rangNorm;
    private String certifikat;
    private List<String> specijalnost = new ArrayList<>();
    private List<Oprema> oprema = new ArrayList<>();
    private State stanje = new MinimalnaOpremaState();

    public String getCertifikat() {
        return certifikat;
    }

    public String getImeRonioca() {
        return imeRonioca;
    }

    public int getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public String getAgencija() {
        return agencija;
    }

    public String getRang() {
        return rang;
    }

    public String getRangNorm() {
        return rangNorm;
    }

    public String getAgencijaNorm() {
        return agencijaNorm;
    }

    public List<String> getSpecijalnost() {
        return specijalnost;
    }

    public List<Oprema> getOprema() {
        return oprema;
    }
    
    public void dodajSpecijalnost(String s){
        this.specijalnost.add(s);
    }
    
    public void dodajOpremu(Oprema s){
        this.oprema.add(s);
    }

    public State getStanje() {
        return stanje;
    }

    public void setStanje(State stanje) {
        this.stanje = stanje;
    }
    
    private Ronioc(RoniocBuilder builder) {
        this.agencija = builder.agencija;
        this.agencijaNorm = builder.agencijaNorm;
        this.godinaRodjenja = builder.godinaRodjenja;
        this.imeRonioca = builder.imeRonioca;
        this.rang = builder.rang;
        this.rangNorm = builder.rangNorm;
        this.certifikat = builder.certifikat;
    }

    public static class RoniocBuilder {

        //required
        private String imeRonioca;
        private int godinaRodjenja;

        //optional
        private String agencija;
        private String agencijaNorm;
        private String rang;
        private String rangNorm;
        private String certifikat;

        public RoniocBuilder(String ime, int godina) {
            this.imeRonioca = ime;
            this.godinaRodjenja = godina;
        }

        public RoniocBuilder setAgencija(String agencija) {
            this.agencija = agencija;
            return this;
        }

        public RoniocBuilder setRang(String rang) {
            this.rang = rang;
            return this;
        }

        public RoniocBuilder setAgencijaNorm(String agencijaNorm) {
            this.agencijaNorm = agencijaNorm;
            return this;
        }

        public RoniocBuilder setRangNorm() {
            this.rangNorm = RankingNorm.normalizirajRazinu(this.agencija, this.rang);
            return this;
        }

        public RoniocBuilder setCertifikat() {
            AgencyFactory a = new AgencyFactory();
            Agency a1 = a.getAgency(this.agencija);
            this.certifikat = a1.getCertificate(this.rang);
            return this;
        }

        public Ronioc build() {
            return new Ronioc(this);
        }
    }

}
