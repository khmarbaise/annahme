package com.soebes.regeln.parser;

import org.testng.annotations.Test;

public class ArtParserTest {

    @Test(expectedExceptions = {UnbekannteArtException.class} )
    public void unbekannteArt() throws UnbekannteArtException {
        ArtParser p = new ArtParser();
        p.parse("TEST");
    }

    @Test
    public void bekannteArt() throws UnbekannteArtException {
        ArtParser p = new ArtParser();
        p.parse("A");
    }
}
