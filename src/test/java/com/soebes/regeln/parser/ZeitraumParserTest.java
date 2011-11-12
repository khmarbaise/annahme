package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.TestBase;
import com.soebes.regeln.annahme.Zeitraum;

public class ZeitraumParserTest extends TestBase {

    @Test
    public void parseVonBisMitAngabeMonatUndJahr() throws UngueltigesDatumException, UnbekannteArtException, UngueltigesDatumFormatException {
        final String regel = "2.2010-02.2010";
        ZeitraumParser zpp = new ZeitraumParser();
        
        Zeitraum result = zpp.parse(regel);

        assertEquals(result.getVon(), parseDateMilliSeconds("01.02.2010 00:00:00.000"));
        assertEquals(result.getBis(), parseDateMilliSeconds("28.02.2010 23:59:59.999"));
    }

    @Test
    public void parseVonBisMitAngabeJahr() throws UngueltigesDatumException, UnbekannteArtException, UngueltigesDatumFormatException {
        final String regel = "2010-2010";
        ZeitraumParser zpp = new ZeitraumParser();
        
        Zeitraum result = zpp.parse(regel);

        assertEquals(result.getVon(), parseDateMilliSeconds("01.01.2010 00:00:00.000"));
        assertEquals(result.getBis(), parseDateMilliSeconds("31.12.2010 23:59:59.999"));
    }

    @Test(expectedExceptions = {UngueltigesDatumException.class})
    public void parseVonBisAngabeMonatUndJahrFehlerhafterMonatVon() throws UngueltigesDatumException, UnbekannteArtException, UngueltigesDatumFormatException {
        final String regel = "13.2010-12.2010";
        ZeitraumParser zpp = new ZeitraumParser();
        
        Zeitraum result = zpp.parse(regel);
        assertNotNull(result);
    }

    @Test(expectedExceptions = {UngueltigesDatumException.class})
    public void parseVonBisAngabeMonatUndJahrFehlerhafterMonatBis() throws UngueltigesDatumException, UnbekannteArtException, UngueltigesDatumFormatException {
        final String regel = "12.2010-13.2010";
        ZeitraumParser zpp = new ZeitraumParser();
        
        Zeitraum result = zpp.parse(regel);
        assertNotNull(result);
    }

    @Test(expectedExceptions = {UngueltigesDatumFormatException.class})
    public void parseVonBisAngabeMonatUndJahrFormatUngueltigVon() throws UngueltigesDatumException, UnbekannteArtException, UngueltigesDatumFormatException {
        final String regel = "121.2010-13.2010";
        ZeitraumParser zpp = new ZeitraumParser();
        
        Zeitraum result = zpp.parse(regel);
        assertNotNull(result);
    }

    @Test(expectedExceptions = {UngueltigesDatumFormatException.class})
    public void parseVonBisAngabeMonatUndJahrFormatUngueltigBis() throws UngueltigesDatumException, UnbekannteArtException, UngueltigesDatumFormatException {
        final String regel = "12.2010-121.2010";
        ZeitraumParser zpp = new ZeitraumParser();
        
        Zeitraum result = zpp.parse(regel);
        assertNotNull(result);
    }

}
