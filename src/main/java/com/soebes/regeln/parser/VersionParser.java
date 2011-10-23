package com.soebes.regeln.parser;

import com.soebes.regeln.annahme.VersionsBereich;

public class VersionParser {

    public VersionsBereich parse(String verisonVonBis) throws UngueltigeAnzahlVersionException, UngueltigeVersionException {
        String[] vonBis = verisonVonBis.split("-");
        
        if (vonBis.length != 2) {
            throw new UngueltigeAnzahlVersionException("Versionsangaben sind nicht genau zwei von-bis");
        }
        
        int von = 0;
        try {
            von = parseVersion(vonBis[0]);
        } catch (UngueltigeVersionException e) {
            throw new UngueltigeVersionException("Version von Angabe ist ungültig.");
        }
        
        int bis = 0;
        try {
            bis = parseVersion(vonBis[1]);
        } catch (UngueltigeVersionException e) {
            throw new UngueltigeVersionException("Version bis Angabe ist ungültig.");
        }

        return new VersionsBereich(von, bis);
    }

    private int parseVersion(String version) throws UngueltigeVersionException {
        int result = 0;
        
        try {
            result = Integer.parseInt(version.trim());
        } catch (NumberFormatException e) {
            throw new UngueltigeVersionException("Ungültige Versionsangabe!");
        }
        
        return result;
    }
}
