package com.soebes.regeln.annahme;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Stellt eine Liste mit den Unterschiedlichen Regeln dar.
 * Wenn keine Regel für eine bestimmte Art vorliegt wird
 * abgelehnt.
 * 
 * @author kama
 *
 */
public class RegelWerk {
    
    private Map<Arten, ArrayList<AnnahmeRegel>> annahmeRegeln;
    
    public RegelWerk() {
        annahmeRegeln = new HashMap<Arten, ArrayList<AnnahmeRegel>>();
    }
    
    public void fuegeRegelHinzu(AnnahmeRegel regel) {
        if (annahmeRegeln.containsKey(regel.getArt())) {
            ArrayList<AnnahmeRegel> regeln = annahmeRegeln.get(regel.getArt());
            regeln.add(regel);
        } else {
            ArrayList<AnnahmeRegel> regeln = new ArrayList<AnnahmeRegel>();
            regeln.add(regel);
            annahmeRegeln.put(regel.getArt(), regeln);
        }
    }
    
    public boolean annahme(Arten art, int version, int detailVersion, Date veranlagungsZeit, Date eingangsZeit) {
        boolean result = false;
        
        if (annahmeRegeln.containsKey(art)) {
            for (AnnahmeRegel regel : annahmeRegeln.get(art)) {
                if (regel.annehmen(art, version, detailVersion, veranlagungsZeit, eingangsZeit)) {
                    result = true;
                }
            }
        }

        return result;
    }
}
