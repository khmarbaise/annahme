package com.soebes.regeln.annahme;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ZeitraumTest extends TestBase {
    private static final Date ZEITRAUM_VON = parseDate("02.01.2011 00:00:02");
    private static final Date ZEITRAUM_BIS = parseDate("02.01.2011 23:59:59");
    
    private Zeitraum zeitraum;
    
    @BeforeClass
    public void beforeClass() {
        zeitraum = new Zeitraum(ZEITRAUM_VON, ZEITRAUM_BIS);
    }

    @Test
    public void eineSekundeVorVon() {
        assertFalse(zeitraum.contains(parseDate("02.01.2011 00:00:01")));
    }
    
    @Test
    public void dieVonGrenzeSelbst() {
        assertTrue(zeitraum.contains(ZEITRAUM_VON));
    }

    @Test
    public void eineSekundeNachDerVonAngabe() {
        assertTrue(zeitraum.contains(parseDate("02.01.2011 00:00:03")));
    }

    @Test
    public void inDerMitte() {
        assertTrue(zeitraum.contains(parseDate("02.01.2011 12:15:03")));
    }
    
    
    @Test
    public void dieBisGrenzeSelbst() {
        assertTrue(zeitraum.contains(ZEITRAUM_BIS));
    }

    @Test
    public void eineSekundeNachBis() {
        assertFalse(zeitraum.contains(parseDate("03.01.2011 00:00:00")));
    }

}
