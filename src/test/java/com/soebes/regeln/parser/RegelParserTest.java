package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.Regel;
import com.soebes.regeln.annahme.TestBase;

public class RegelParserTest extends TestBase {

    @Test
    public void nullRegel() throws UnbekannteRegelException {
        RegelParser rp = new RegelParser();
        Regel regel = rp.parse("");
        
        assertEquals(regel, Regel.NULL_REGEL);
        
    }

    @Test
    public void vzEingPlusEins() throws UnbekannteRegelException {
        RegelParser rp = new RegelParser();
        Regel regel = rp.parse(Regel.REGEL_VZ_EING_PLUS_EINS);
        
        assertEquals(regel, Regel.VZ_EING_PLUS_EINS);
        
    }
}
