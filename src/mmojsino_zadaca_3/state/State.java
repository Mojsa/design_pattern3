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
public interface State {
    public void setOprema(Ronioc r, List<Oprema> oprema, UronPodaci up);
}
