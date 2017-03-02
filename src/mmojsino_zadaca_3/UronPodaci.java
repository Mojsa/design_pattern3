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
public class UronPodaci {
    private int dubina;
    private int noc;
    private int snimanja;
    private int temp;

    public UronPodaci(int dubina, int noc, int snimanja, int temp) {
        this.dubina = dubina;
        this.noc = noc;
        this.snimanja = snimanja;
        this.temp = temp;
    }

    public int getDubina() {
        return dubina;
    }

    public int getNoc() {
        return noc;
    }

    public int getSnimanja() {
        return snimanja;
    }

    public int getTemp() {
        return temp;
    }

    
    
}
