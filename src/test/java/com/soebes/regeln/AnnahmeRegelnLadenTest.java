package com.soebes.regeln;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.AnnahmeRegel;
import com.soebes.regeln.annahme.AnnahmeRegelWerk;
import com.soebes.regeln.annahme.Arten;
import com.soebes.regeln.annahme.TestBase;
import com.soebes.regeln.annahme.VersionsBereichVonBisVertauschtExepction;
import com.soebes.regeln.parser.AnnahmeRegelParser;
import com.soebes.regeln.parser.UnbekannteArtException;
import com.soebes.regeln.parser.UnbekannteRegelException;
import com.soebes.regeln.parser.UngueltigeAnzahlVersionException;
import com.soebes.regeln.parser.UngueltigeVersionException;
import com.soebes.regeln.parser.UngueltigesDatumException;
import com.soebes.regeln.parser.UngueltigesDatumFormatException;

public class AnnahmeRegelnLadenTest extends TestBase {

    @Test
    public void regelnLaden() throws UngueltigesDatumException, 
        UnbekannteArtException, 
        UngueltigeAnzahlVersionException, 
        UngueltigeVersionException, 
        UnbekannteRegelException, 
        UngueltigesDatumFormatException, 
        VersionsBereichVonBisVertauschtExepction {
        String[] regeln = {
                "A, 02.2010-09.2099, 1-2, 1-3,VZ=Eing+1",
                "A, 02.2010-09.2099, 1-2, 4-7,deaktiviert",
                "B, 02.2010-09.2099, 1-2, 3-7,VZ=Eing+1",
                "C, 02.2010-09.2099, 1-2, 1-3,VZ=Eing+1",
        };
        
        AnnahmeRegelWerk regelWerk = new AnnahmeRegelWerk();
        AnnahmeRegelParser parser = new AnnahmeRegelParser();
        
        for (String regelItem : regeln) {
            if (parser.isDeaktiviert(regelItem)) {
                continue;
            }

            AnnahmeRegel annahmeRegel = parser.parse(regelItem);
            regelWerk.fuegeRegelHinzu(annahmeRegel);
        }

        assertTrue(regelWerk.annahme(Arten.A, 1, 2, parseDate("01.03.2010 12:30:45"), parseDate("02.03.2011 11:45:00")));
        assertFalse(regelWerk.annahme(Arten.A, 1, 4, parseDate("01.03.2010 12:30:45"), parseDate("02.03.2011 11:45:00")));
        assertFalse(regelWerk.annahme(Arten.B, 2, 2, parseDate("01.03.2010 12:30:45"), parseDate("02.03.2011 11:45:00")));
    }
}
