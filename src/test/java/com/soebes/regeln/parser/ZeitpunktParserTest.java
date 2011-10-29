package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;

import java.util.Date;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.TestBase;

public class ZeitpunktParserTest extends TestBase {

    @Test
    public void parseVonMonatUndJahr() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "02.2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parseVon(regel);

        assertEquals(result, parseDateMilliSeconds("01.02.2010 00:00:00.000"));
    }

    @Test
    public void parseVonMonatUndJahrOhneFuehrendeNull() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "2.2010";
        ZeitpunktParser zpp = new ZeitpunktParser();

        Date result = zpp.parseVon(regel);

        assertEquals(result, parseDateMilliSeconds("01.02.2010 00:00:00.000"));
    }

    @Test
    public void parseVonNurJahresAngabe() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parseVon(regel);

        assertEquals(result, parseDateMilliSeconds("01.01.2010 00:00:00.000"));
    }

    @Test
    public void parseBisNurJahresAngabe() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parseBis(regel);
        Date expected = parseDateMilliSeconds("31.12.2010 23:59:59.999");
        assertEquals(result, expected);
    }

    @Test
    public void parseBisMonatUndJahrAngabe() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "02.2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parseBis(regel);
        Date expected = parseDateMilliSeconds("28.02.2010 23:59:59.999");
        assertEquals(result, expected);
    }


//    @Test
//    public void nochmalPruefenAufDieFalscheAngabe() throws UngueltigesDatumException, UnbekannteArtException {
//        final String regel = "13.2010";
//        ZeitpunktParser zpp = new ZeitpunktParser();
//        
//        Date result = zpp.parse(regel);
//
//        Date expected = parseDate("01.12.2010 00:00:00");
//        assertEquals(result, expected);
//    }
}
