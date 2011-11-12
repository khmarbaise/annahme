package com.soebes.regeln.annahme;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class VersionsBereichTest {

    @Test
    public void containsMitUnterschiedlichenGrenzen() throws VersionsBereichVonBisVertauschtExepction {
        VersionsBereich vonBis = new VersionsBereich(1, 3);
        assertTrue(vonBis.contains(1));
        assertTrue(vonBis.contains(2));
        assertTrue(vonBis.contains(3));

        assertFalse(vonBis.contains(0));
        assertFalse(vonBis.contains(4));
    }

    @Test
    public void containsMitIdentischenGrenzen() throws VersionsBereichVonBisVertauschtExepction {
        VersionsBereich vonBis = new VersionsBereich(2, 2);
        assertTrue(vonBis.contains(2));

        assertFalse(vonBis.contains(1));
        assertFalse(vonBis.contains(3));

    }
}
