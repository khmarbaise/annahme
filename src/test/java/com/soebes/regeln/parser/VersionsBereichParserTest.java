package com.soebes.regeln.parser;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.soebes.regeln.annahme.TestBase;
import com.soebes.regeln.annahme.VersionsBereich;
import com.soebes.regeln.annahme.VersionsBereichVonBisVertauschtExepction;

public class VersionsBereichParserTest extends TestBase {

    @Test
    public void gueltigeVersionVonBis() throws UngueltigeAnzahlVersionException, UngueltigeVersionException, VersionsBereichVonBisVertauschtExepction {
        VersionsBereichParser vp = new VersionsBereichParser();
        VersionsBereich vb = vp.parse("1-2");
        assertEquals(vb, new VersionsBereich(1,2));
    }

    @Test(expectedExceptions = {VersionsBereichVonBisVertauschtExepction.class})
    public void ungueltigVonLiegtNachBis() throws UngueltigeAnzahlVersionException, UngueltigeVersionException, VersionsBereichVonBisVertauschtExepction {
        VersionsBereichParser vp = new VersionsBereichParser();
        VersionsBereich vb = vp.parse("2-1");
        assertEquals(vb, new VersionsBereich(1,2));
    }

    @Test(expectedExceptions = {UngueltigeAnzahlVersionException.class})
    public void ungueltigeAnzahl() throws UngueltigeAnzahlVersionException, UngueltigeVersionException, VersionsBereichVonBisVertauschtExepction {
        VersionsBereichParser vp = new VersionsBereichParser();
        VersionsBereich vb = vp.parse("1-1-2");
        assertEquals(vb, new VersionsBereich(1,2));
    }

    @Test(expectedExceptions = {UngueltigeVersionException.class})
    public void ungueltigeVersionVon() throws UngueltigeAnzahlVersionException, UngueltigeVersionException, VersionsBereichVonBisVertauschtExepction {
        VersionsBereichParser vp = new VersionsBereichParser();
        VersionsBereich vb = vp.parse("A-2");
        assertEquals(vb, new VersionsBereich(1,2));
    }

    @Test(expectedExceptions = {UngueltigeVersionException.class})
    public void ungueltigeVersionBis() throws UngueltigeAnzahlVersionException, UngueltigeVersionException, VersionsBereichVonBisVertauschtExepction {
        VersionsBereichParser vp = new VersionsBereichParser();
        VersionsBereich vb = vp.parse("1-A");
        assertEquals(vb, new VersionsBereich(1,2));
    }
}
