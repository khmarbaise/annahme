package com.soebes.regeln.annahme;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ZeitraumTest extends TestBase {

    private Zeitraum zeitraum;
    
    @BeforeClass
    public void beforeClass() {
        zeitraum = new Zeitraum(parseDate("02.01.2011 00:00:02"), parseDate("02.01.2011 23:59:59"));
    }

    @Test
    public void eineSekundeGroesserAlsDieVonAngabe() {
        assertTrue(zeitraum.contains(parseDate("02.01.2011 00:00:03")));
    }

    @Test
    public void inDerMitte() {
        assertTrue(zeitraum.contains(parseDate("02.01.2011 12:15:03")));
    }

    @Test
    public void aufDerVonGrenze() {
        assertTrue(zeitraum.contains(parseDate("02.01.2011 00:00:02")));
    }

    @Test
    public void aufDerBisGrenze() {
        assertTrue(zeitraum.contains(parseDate("02.01.2011 23:59:59")));
    }

    @Test
    public void eineSekundeVorVon() {
        assertFalse(zeitraum.contains(parseDate("02.01.2011 00:00:01")));
    }

    @Test
    public void eineSekundeNachVon() {
        assertFalse(zeitraum.contains(parseDate("03.01.2011 00:00:00")));
    }

}
