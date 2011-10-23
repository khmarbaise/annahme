package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;

import java.util.Date;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.TestBase;

public class ZeitpunktParserTest extends TestBase {

    @Test
    public void monatUndJahr() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "02.2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parse(regel);

        assertEquals(result, parseDate("01.02.2010 00:00:00"));
    }

    @Test
    public void monatUndJahrOhneFuehrendeNull() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "2.2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parse(regel);

        assertEquals(result, parseDate("01.02.2010 00:00:00"));
    }


    @Test
    public void nurJahresAngabe() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parse(regel);

        assertEquals(result, parseDate("01.01.2010 00:00:00"));
    }

    @Test
    public void nurJahresAngabeBis() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parseBis(regel);
        Date expected = parseDate("31.12.2010 23:59:59");
        assertEquals(result, expected);
    }

    @Test
    public void monatUndJahrAngabeBis() throws UngueltigesDatumException, UnbekannteArtException {
        final String regel = "02.2010";
        ZeitpunktParser zpp = new ZeitpunktParser();
        
        Date result = zpp.parseBis(regel);
        Date expected = parseDate("28.02.2010 23:59:59");
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
