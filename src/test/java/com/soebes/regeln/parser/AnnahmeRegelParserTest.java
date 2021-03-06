package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.AnnahmeRegel;
import com.soebes.regeln.annahme.Arten;
import com.soebes.regeln.annahme.Regel;
import com.soebes.regeln.annahme.TestBase;
import com.soebes.regeln.annahme.VersionsBereich;
import com.soebes.regeln.annahme.VersionsBereichVonBisVertauschtExepction;
import com.soebes.regeln.annahme.Zeitraum;

public class AnnahmeRegelParserTest extends TestBase {

    @Test
    public void parseVollstaendigeRegel() throws UngueltigesDatumException, UnbekannteArtException, UngueltigeAnzahlVersionException, UngueltigeVersionException, UnbekannteRegelException, UngueltigesDatumFormatException, VersionsBereichVonBisVertauschtExepction {
        final String regel = "A, 02.2010-09.2099, 1-2, 1-3,VZ=Eing+1";
        AnnahmeRegelParser arp = new AnnahmeRegelParser();
        AnnahmeRegel geparsteRegel = arp.parse(regel);

        assertTrue(geparsteRegel.getArt().equals(Arten.A));
        assertEquals(geparsteRegel.getVersionVonBis(), new VersionsBereich(1, 2));
        assertEquals(geparsteRegel.getDetailVersionVonBis(), new VersionsBereich(1, 3));

        assertEquals(
            geparsteRegel.getAnnahmeZeitraum(), 
            new Zeitraum(parseDateMilliSeconds("01.02.2010 00:00:00.000"), parseDateMilliSeconds("30.09.2099 23:59:59.999"))
        );
        assertEquals(geparsteRegel.getRegel(), Regel.VZ_EING_PLUS_EINS);
    }

    @Test
    public void parseUnvollstaendigeRegel() throws UngueltigesDatumException, UnbekannteArtException, UngueltigeAnzahlVersionException, UngueltigeVersionException, UnbekannteRegelException, UngueltigesDatumFormatException, VersionsBereichVonBisVertauschtExepction {
        final String regel = "A, 02.2010-09.2099, 1-2, 1-3";
        AnnahmeRegelParser arp = new AnnahmeRegelParser();
        AnnahmeRegel geparsteRegel = arp.parse(regel);

        assertTrue(geparsteRegel.getArt().equals(Arten.A));
        assertEquals(geparsteRegel.getVersionVonBis(), new VersionsBereich(1, 2));
        assertEquals(geparsteRegel.getDetailVersionVonBis(), new VersionsBereich(1, 3));

        assertEquals(
            geparsteRegel.getAnnahmeZeitraum(), 
            new Zeitraum(parseDateMilliSeconds("01.02.2010 00:00:00.000"), parseDateMilliSeconds("30.09.2099 23:59:59.999"))
        );
        assertEquals(geparsteRegel.getRegel(), Regel.NULL_REGEL);
    }

    @Test
    public void parseUnvollstaendigeRegelIsDeaktiviertFalse()  {
        final String regel = "A, 02.2010-09.2099, 1-2, 1-3";
        AnnahmeRegelParser arp = new AnnahmeRegelParser();
        assertFalse(arp.isDeaktiviert(regel));
    }

    @Test
    public void parseUnvollstaendigeRegelIsDeaktiviertTrue()  {
        final String regel = "A, 02.2010-09.2099, 1-2, 1-3,deaktiviert";
        AnnahmeRegelParser arp = new AnnahmeRegelParser();
        assertTrue(arp.isDeaktiviert(regel));
    }
}
