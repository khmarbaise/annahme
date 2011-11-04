package com.soebes.regeln.parser;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.Arten;

public class ArtParserTest {

    @Test(expectedExceptions = {UnbekannteArtException.class} )
    public void unbekannteArt() throws UnbekannteArtException {
        ArtParser p = new ArtParser();
        p.parse("TEST");
    }

    @Test
    public void bekannteArt() throws UnbekannteArtException {
        ArtParser p = new ArtParser();
        Arten a = p.parse("A");
        assertTrue(a.equals(Arten.A));
    }

    @Test
    public void bekannteArtKleinGeschrieben() throws UnbekannteArtException {
        ArtParser p = new ArtParser();
        Arten a = p.parse("a");
        assertTrue(a.equals(Arten.A));
    }
}
