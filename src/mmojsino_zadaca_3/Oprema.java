/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3;

/**
 *
 * @author Mariofil
 */
public class Oprema {

    private String sifraOpreme;
    private String nazivOpreme;
    private int temp;
    private char trebaKapuljacu;
    private char trebaPododijelo;
    private char trebaNocniUron;
    private char trebaSnimanje;
    private int brojKomada;

    private Oprema(OpremaBuilder b) {
        this.sifraOpreme = b.sifraOpreme;
        this.nazivOpreme = b.nazivOpreme;
        this.temp = b.temp;
        this.trebaKapuljacu = b.trebaKapuljacu;
        this.trebaPododijelo = b.trebaPododijelo;
        this.trebaNocniUron = b.trebaNocniUron;
        this.trebaSnimanje = b.trebaSnimanje;
        this.brojKomada = b.brojKomada;
    }

    public String getSifraOpreme() {
        return sifraOpreme;
    }

    public String getNazivOpreme() {
        return nazivOpreme;
    }

    public int getTemp() {
        return temp;
    }

    public char getTrebaKapuljacu() {
        return trebaKapuljacu;
    }

    public char getTrebaPododijelo() {
        return trebaPododijelo;
    }

    public char getTrebaNocniUron() {
        return trebaNocniUron;
    }

    public char getTrebaSnimanje() {
        return trebaSnimanje;
    }

    public int getBrojKomada() {
        return brojKomada;
    }

    public void setBrojKomada(int brojKomada) {
        this.brojKomada = brojKomada;
    }
    
    public static class OpremaBuilder {

        private String sifraOpreme;
        private String nazivOpreme;
        private int temp;
        private char trebaKapuljacu;
        private char trebaPododijelo;
        private char trebaNocniUron;
        private char trebaSnimanje;
        private int brojKomada;

        public OpremaBuilder() {

        }

        public OpremaBuilder setSifraOpreme(String sifraOpreme) {
            this.sifraOpreme = sifraOpreme;
            return this;
        }

        public OpremaBuilder setNazivOpreme(String nazivOpreme) {
            this.nazivOpreme = nazivOpreme;
            return this;
        }

        public OpremaBuilder setTemp(int temp) {
            this.temp = temp;
            return this;
        }

        public OpremaBuilder setTrebaKapuljacu(char trebaKapuljacu) {
            this.trebaKapuljacu = trebaKapuljacu;
            return this;
        }

        public OpremaBuilder setTrebaPododijelo(char trebaPododijelo) {
            this.trebaPododijelo = trebaPododijelo;
            return this;
        }

        public OpremaBuilder setTrebaNocniUron(char trebaNocniUron) {
            this.trebaNocniUron = trebaNocniUron;
            return this;
        }

        public OpremaBuilder setTrebaSnimanje(char trebaSnimanje) {
            this.trebaSnimanje = trebaSnimanje;
            return this;
        }

        public OpremaBuilder setBrojKomada(int brojKomada) {
            this.brojKomada = brojKomada;
            return this;
        }
        
        public Oprema build(){
            return new Oprema(this);
        }

    }

}
