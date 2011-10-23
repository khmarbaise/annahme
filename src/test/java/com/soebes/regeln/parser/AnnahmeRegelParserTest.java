package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.AnnahmeRegel;
import com.soebes.regeln.annahme.Arten;
import com.soebes.regeln.annahme.TestBase;
import com.soebes.regeln.annahme.Zeitraum;

public class AnnahmeRegelParserTest extends TestBase {

    @Test
    public void unbekannteArt() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "A, 02.2010-09.2099, 1-2, 1-3,VZ=Eing+1";
        AnnahmeRegelParser arp = new AnnahmeRegelParser();
        AnnahmeRegel geparsteRegel = arp.parse(regel);

        assertTrue(geparsteRegel.getArt().equals(Arten.A));
        assertEquals(geparsteRegel.getAnnahmeZeitraum(), new Zeitraum(parseDate("01.02.2010 00:00:00"), parseDate("30.09.2099 23:59:59")));
    }

}
