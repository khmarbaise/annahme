package com.soebes.regeln.parser;

import com.soebes.regeln.annahme.Arten;

public class ArtParser {

    public Arten parse(String content) throws UnbekannteArtException {
        Arten art = Arten.A;
        
        try {
            art = Arten.valueOf(content.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnbekannteArtException("Die angegebene Art " + content + " ist unbekannt.");
        }
        
        return art;
    }
}
