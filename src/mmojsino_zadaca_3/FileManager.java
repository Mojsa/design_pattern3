/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_3;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mmojsino_zadaca_3.mvc.Model;
import mmojsino_zadaca_3.Oprema;

/**
 *
 * @author Mario
 */
public class FileManager {

    File writeFile = null;

    public void openFile(String path, Model m) throws IOException {
        Path filePath = Paths.get(path);
        Scanner scanner = new Scanner(filePath);
        String[] parts;
        if (path.contains("ronioci")) {
            while (scanner.hasNext()) {
                if (scanner.hasNext()) {
                    parts = scanner.next().split(";");
                    if (parts.length == 4) {
                        m.addRonioc(new Ronioc.RoniocBuilder(parts[0], Integer.parseInt(parts[3]))
                                .setAgencija(parts[1])
                                .setAgencijaNorm(RankingNorm.getAgencije()[0])
                                .setRang(parts[2])
                                .setRangNorm()
                                .setCertifikat()
                                .build());
                    } else {
                        System.out.println("Neispravan zapis, preskacem liniju.");
                    }

                } else {
                    scanner.next();
                }
            }
        }
        scanner.close();
    }

    /**
     *
     * @param path
     * @return
     * @throws IOException
     */
    public ListMultimap procitajSpecijalnosti(String path) throws IOException {
        ListMultimap<String, String> specijalnosti = ArrayListMultimap.create();
        Path filePath = Paths.get(path);
        Scanner scanner = new Scanner(filePath);
        String[] parts;
        if (path.contains("specijalno")) {
            while (scanner.hasNext()) {
                if (scanner.hasNext()) {
                    parts = scanner.nextLine().split(";");
                    specijalnosti.put(parts[0], parts[1]);
                } else {
                    scanner.nextLine();
                }
            }
        }
        return specijalnosti;
    }

    public List<Oprema> procitajOpremu(String path) throws IOException {
        List<Oprema> listaOpreme = new ArrayList<>();
        Path filePath = Paths.get(path);
        Scanner scanner = new Scanner(filePath);
        String[] parts;
        int tempTemp;
        if (path.contains("oprema")) {
            while (scanner.hasNext()) {
                if (scanner.hasNext()) {
                    parts = scanner.nextLine().split(";");
                    if (parts.length == 8) {
                        tempTemp = 0;
                        if(parts[2].equals("#")){
                            tempTemp = -5;
                        }else{
                            tempTemp = Integer.parseInt(parts[2]);
                        }
                        listaOpreme.add(new Oprema.OpremaBuilder()
                                .setSifraOpreme(parts[0])
                                .setNazivOpreme(parts[1])
                                .setTemp(tempTemp)
                                .setTrebaKapuljacu(parts[3].charAt(0))
                                .setTrebaPododijelo(parts[4].charAt(0))
                                .setTrebaNocniUron(parts[5].charAt(0))
                                .setTrebaSnimanje(parts[6].charAt(0))
                                .setBrojKomada(Integer.parseInt(parts[7]))
                                .build());
                    }else{
                        //System.out.println("Neispravan redak.");
                    }
                } else {
                    scanner.nextLine();
                }
            }
        }
        return listaOpreme;
    }
}
