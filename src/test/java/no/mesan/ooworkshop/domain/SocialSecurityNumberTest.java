package no.mesan.ooworkshop.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SocialSecurityNumberTest {

    private static boolean make(long l) {
        try {
            SocialSecurityNumber.of(l);
            return true;
        }
        catch (IllegalArgumentException dontCare) {
            return false;
        }
    }

    @Test
    public void validSocialSecurityNumber() throws Exception {
        assertTrue(make(17050355543L));
        assertFalse(make(45114304788L));
        assertTrue( make(12047566787L));
        assertFalse(make(120470566787L));
        assertFalse(make(24128025489L));
        assertFalse(make(1404721355L));
        assertFalse(make(24128025631L));
    }
}