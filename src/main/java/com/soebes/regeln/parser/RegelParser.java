package com.soebes.regeln.parser;

import com.soebes.regeln.annahme.Regel;


public class RegelParser {
    public final static String DEAKTIVIERT = "deaktiviert";

    public Regel parse(String contents) throws UnbekannteRegelException {
        Regel result = Regel.NULL_REGEL;
        
        if (contents.trim().isEmpty()) {
            result = Regel.NULL_REGEL;
        } else if (contents.equalsIgnoreCase(Regel.REGEL_VZ_EING_PLUS_EINS)) {
            result = Regel.VZ_EING_PLUS_EINS;
        } else if (contents.equalsIgnoreCase(DEAKTIVIERT)) {
            result = Regel.DEAKTIVIERT;
        } else {
            throw new UnbekannteRegelException("Die Regel ist unbekannt.!");
        }
        return result;
    }
}
