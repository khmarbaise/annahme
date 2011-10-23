package com.soebes.regeln.parser;

import java.util.Date;

import com.soebes.regeln.annahme.AnnahmeRegel;
import com.soebes.regeln.annahme.VersionsBereich;
import com.soebes.regeln.annahme.Zeitraum;

public class AnnahmeRegelParser {

 // AA, 02.2010-09.2099, 1-2, 1-3, [XRegeln|gelöscht|(leer)]
//  !     !        !     !    !       +------------------------- Regel
//  !     !        !     !    +--------------------------------- Detail Version (von-bis)
//  !     !        !     +-------------------------------------- Version (von-bis)
//  !     !        +-------------------------------------------- Annahme VZ Zeitraum bis (Zeitpunkt hier Monat.Jahr)
//  !     +----------------------------------------------------- Annahme VZ Zeitraum von (Zeitpunkt hier Monat.Jahr)
//  +----------------------------------------------------------- Art
//

    public AnnahmeRegelParser() {
//        annahmeRegel = new AnnahmeRegel();
    }

    public AnnahmeRegel parse(String regel) 
        throws UngueltigesDatumException, 
                UnbekannteArtException, 
                UngueltigeAnzahlVersionException, 
                UngueltigeVersionException {
        AnnahmeRegel resultRegel = new AnnahmeRegel();
        String[] elemente = regel.split(",");
        
     
        ArtParser artParse = new ArtParser();
        resultRegel.setArt(artParse.parse(elemente[0]));

        parseZeitRaum(resultRegel, elemente);

        VersionParser versionVonBisParser = new VersionParser ();
        resultRegel.setVersionVonBis(versionVonBisParser.parse(elemente[2]));
        
        resultRegel.setDetailVersionVonBis(versionVonBisParser.parse(elemente[3]));

        return resultRegel;
        
    }

    private void parseZeitRaum(AnnahmeRegel resultRegel, String[] elemente) throws UngueltigesDatumException {
        String[] zeitPunkte = elemente[1].split("-");
        ZeitpunktParser zeitPunktParser = new ZeitpunktParser();

        Date von = zeitPunktParser.parseVon(zeitPunkte[0]);

        Date bis = zeitPunktParser.parseBis(zeitPunkte[1]);
        
        resultRegel.setAnnahmeZeitraum(new Zeitraum(von, bis));
    }
}
