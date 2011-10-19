package com.soebes.regeln.annahme;

import java.util.Date;

/**
 * [VZ=Eing+1|gelöscht|(leer)]
 * 
 * @author kama
 *
 */
public class Regel {
    /**
     * <code>VZ=Eing+1</code> bedeutet, <code>VZ=Eingangszeitpunkt + 1 Jahr</code>
     * 
     */
    public static final String REGEL_VZ_EING_PLUS_EINS = "VZ=Eing+1";
    
    public static final Regel NULL_REGEL = new Regel(null);
    public static final Regel VZ_EING_PLUS_EINS = new Regel(REGEL_VZ_EING_PLUS_EINS);

    private String regelInhalt;

    public Regel(String regelInhalt) {
        super();
        this.regelInhalt = regelInhalt;
    }

    public boolean annahme(Date veranlagungszeitPunkt, Date eingangsZeitpunkt) {
        if (getRegelInhalt() == null) {
            return true;
        }
        
        if (this.equals(VZ_EING_PLUS_EINS)) {
            //FIXME: Das stimmt noch nicht! Muss das nächste Jahr sein!!! (eventuell Eing+1?)
            if (eingangsZeitpunkt.after(veranlagungszeitPunkt)) {
                return true;
            }
        }
        
        return false;
    }

    public String getRegelInhalt() {
        return regelInhalt;
    }

    public void setRegelInhalt(String regelInhalt) {
        this.regelInhalt = regelInhalt;
    }
    
    
}
