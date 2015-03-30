package com.soebes.regeln.annahme;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VersionsBereichTest
{

    public class GrenzenPruefen
    {
        private VersionsBereich vonBis;

        @BeforeMethod
        public void beforeMethod()
            throws VersionsBereichVonBisVertauschtExepction
        {
            vonBis = new VersionsBereich( 1, 3 );
        }

        @Test
        public void checkValueAtLowerLimit()
        {
            assertThat( vonBis.contains( 1 ) ).isTrue();
        }

        @Test
        public void checkValueInTheMiddleOfTheLimits()
        {
            assertThat( vonBis.contains( 2 ) ).isTrue();
        }

        @Test
        public void checkValueAtUpperLimit()
        {
            assertThat( vonBis.contains( 3 ) ).isTrue();
        }

        @Test
        public void checkValueLessThanLowerLimit()
        {
            assertThat( vonBis.contains( 0 ) ).isFalse();
        }

        @Test
        public void checkValueGreaterThanUpperLimit()
        {
            assertThat( vonBis.contains( 4 ) ).isFalse();
        }
    }

    public class CheckUpperAndLowerLimitAreEqual
    {
        private VersionsBereich vonBis;

        @BeforeMethod
        public void beforeMethod()
            throws VersionsBereichVonBisVertauschtExepction
        {
            vonBis = new VersionsBereich( 2, 2 );
        }

        @Test
        public void checkTheOnlyValidValue()
        {
            assertThat( vonBis.contains( 2 ) ).isTrue();
        }

        @Test
        public void checkLessThanLowerLimit()
        {
            assertThat( vonBis.contains( 1 ) ).isFalse();
        }

        @Test
        public void checkGreaterThanUpperLimit()
        {
            assertThat( vonBis.contains( 3 ) ).isFalse();
        }
    }
}
