/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Mario
 */
public class Uron  {

    //required
    private String datum;
    private String vrijeme;
    private int maxDubina;
    private int brojRonioca;
    //optional
    private List<Sastav> uronGrupa = new ArrayList<>();
    private TreeMap mjeraSigurnosti;


    private Uron(UronBuilder b) {
        this.datum = b.datum;
        this.vrijeme = b.vrijeme;
        this.maxDubina = b.maxDubina;
        this.brojRonioca = b.brojRonioca;
        this.uronGrupa = b.uronGrupa;
        this.mjeraSigurnosti = b.mjeraSigurnosti;
    }

    public TreeMap getMjeraSigurnosti() {
        return mjeraSigurnosti;
    }

    public String getDatum() {
        return datum;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public int getMaxDubina() {
        return maxDubina;
    }

    public int getBrojRonioca() {
        return brojRonioca;
    }

    public List<Sastav> getUronGrupa() {
        return uronGrupa;
    }

    public static class UronBuilder {

        //required
        private String datum;
        private String vrijeme;
        private int maxDubina;
        private int brojRonioca;

        //optional
        private List<Sastav> uronGrupa = new ArrayList<>();
        private TreeMap mjeraSigurnosti;

        public UronBuilder(String datum, String vrijeme, int maxDubina, int brojRonioca) {
            this.datum = datum;
            this.vrijeme = vrijeme;
            this.maxDubina = maxDubina;
            this.brojRonioca = brojRonioca;
        }

        public UronBuilder postaviGrupu(List<Sastav> s) {
            this.uronGrupa = s;
            return this;
        }

        public UronBuilder postaviMjeruSigurnosti(TreeMap m) {
            this.mjeraSigurnosti = new TreeMap();
            this.mjeraSigurnosti = m;
            return this;
        }

        public Uron build() {
            return new Uron(this);
        }
    }
}
