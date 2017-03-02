/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import mmojsino_zadaca_3.RankingNorm;
import mmojsino_zadaca_3.Ronioc;
import mmojsino_zadaca_3.Sastav;
import mmojsino_zadaca_3.Uron;

/**
 *
 * @author Mario
 */
public class AlgorithmMaxDepth {

//maks uron (maksimalno dopustena dubina u odnosu na planiranu)
    public TreeMap generirajPlan(Uron u, List<Ronioc> listaOdabranih) {
        TreeMap tm = new TreeMap();
        List<Sastav> listSastav = new ArrayList<>();
        int maxBroj = u.getMaxDubina();
        List<Ronioc> nesretnici = new ArrayList<>();
        List<Ronioc> sretnici = new ArrayList<>();

        for (Ronioc r : listaOdabranih) {
            if (pronadjiDubinu(Integer.parseInt(r.getRangNorm().substring(1, 2)), false)
                    < maxBroj
                    && Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r.getRangNorm())) {
                nesretnici.add(r);
            } else if (pronadjiDubinu(Integer.parseInt(r.getRangNorm().substring(1, 2)), false)
                    >= maxBroj
                    && Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r.getRangNorm())) {
                sretnici.add(r);
            } else if (Arrays.asList(RankingNorm.getRazineProfi()).contains(r.getRangNorm())) {
                sretnici.add(r);
            }
        }
        Iterator<Ronioc> itSretnici = sretnici.iterator();
        Iterator<Ronioc> itNesretnici = nesretnici.iterator();
        Sastav sastav = new Sastav();
        while (true) {
            if (!itNesretnici.hasNext() && !itSretnici.hasNext()) {
                listSastav.add(sastav);
                break;
            }
            if (sastav.mozeJosRonioca() && itNesretnici.hasNext()) {
                sastav.dodajRoniocaSastav(itNesretnici.next());
            }
            if (sastav.mozeJosRonioca() && itSretnici.hasNext()) {
                sastav.dodajRoniocaSastav(itSretnici.next());
            }
            if (!sastav.mozeJosRonioca()) {
                listSastav.add(sastav);
                sastav = new Sastav();
            }
        }
        float ukupno = 0;
        for (Sastav s : listSastav) {
            s.postaviDubinu(vratiDubinu(listSastav));
        }
        tm.put("Mjera", ukupno);
        tm.put("Lista", listSastav);
        return tm;
    }

    private int vratiDubinu(List<Sastav> sastav) {
        int indexRekr = 0;
        boolean rekreacijski = false;
        for (Sastav s : sastav) {
            List<Ronioc> lista = s.getLista();
            for (Ronioc r : lista) {
                if (Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r.getRangNorm())) {
                    if (lista.indexOf(r) > 0) {
                        if (indexRekr > -1 && (Integer.parseInt(lista.get(indexRekr).getRangNorm().substring(1, 2)))
                                < (Integer.parseInt(r.getRangNorm().substring(1, 2)))) {
                            rekreacijski = true;
                        } else {
                            indexRekr = lista.indexOf(r);
                            rekreacijski = true;

                        }
                    } else {
                        indexRekr = lista.indexOf(r);
                    }
                }
            }
        }
        if (!rekreacijski) {
            indexRekr = 3;
        }
        return pronadjiDubinu(indexRekr + 1, true);
    }

    public int pronadjiDubinu(int broj, boolean profiMix) {
        int dodatak = 0;
        if (profiMix) {
            dodatak = 10;
        }
        switch (broj) {
            case 0:
                return 0;
            case 1:
                return 10 + dodatak;
            case 2:
                return 30 + dodatak;
            default:
                return 40;
        }
    }
}
