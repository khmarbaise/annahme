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
public class AnnahmeRegelWerk {

    private Map<Arten, ArrayList<AnnahmeRegel>> annahmeRegeln;
    
    public AnnahmeRegelWerk() {
        annahmeRegeln = new HashMap<Arten, ArrayList<AnnahmeRegel>>();
    }
    
    public void fuegeRegelHinzu(AnnahmeRegel regel) {
        if (istRegelVorhandenFuerArt(regel.getArt())) {
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
        
        if (istRegelVorhandenFuerArt(art)) {
            for (AnnahmeRegel regel : annahmeRegeln.get(art)) {
                if (regel.annehmen(art, version, detailVersion, veranlagungsZeit, eingangsZeit)) {
                    result = true;
                }
            }
        }

        return result;
    }

    private boolean istRegelVorhandenFuerArt(Arten art) {
        return annahmeRegeln.containsKey(art);
    }
}
