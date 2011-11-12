package com.soebes.regeln.annahme;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AnnahmeRegelTest extends TestBase {

    private AnnahmeRegel annahme;

    @BeforeClass
    public void beforeClass() throws VersionsBereichVonBisVertauschtExepction {
        annahme = new AnnahmeRegel(
            Arten.A, 
            new Zeitraum(parseDate("01.07.2010 00:00:00"), parseDate("30.06.2011 23:59:59")), 
            new VersionsBereich(2, 3), 
            new VersionsBereich(1, 2), 
            Regel.NULL_REGEL
          );
    }

    @Test
    public void annahmeKorrekteArt() {
        assertTrue(annahme.annehmen(Arten.A, 2, 1, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }

    @Test
    public void annahmeKorrekteVersion() {
        assertTrue(annahme.annehmen(Arten.A, 3, 1, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }

    @Test
    public void annahmeKorrekteDetailVersion() {
        assertTrue(annahme.annehmen(Arten.A, 3, 2, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }

    @Test
    public void ablehnungWegenArt() {
        assertFalse(annahme.annehmen(Arten.B, 2, 1, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }
    
    @Test
    public void ablehnungWegenVersionZuKlein() {
        assertFalse(annahme.annehmen(Arten.A, 1, 1, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));

    }

    @Test
    public void ablehnungWegenVersionZuGross() {
        assertFalse(annahme.annehmen(Arten.A, 4, 1, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }
    
    @Test
    public void ablehnungWegenDetailVersionZuGross() {
        assertFalse(annahme.annehmen(Arten.A, 2, 3, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }

    @Test
    public void ablehnungWegenDetailVersionZuKlein() {
        assertFalse(annahme.annehmen(Arten.A, 2, 0, parseDate("01.01.2011 15:00:00"), parseDate("02.02.2011 16:00:00")));
    }
}
