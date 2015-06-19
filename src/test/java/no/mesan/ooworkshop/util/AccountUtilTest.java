package no.mesan.ooworkshop.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountUtilTest {

    @Test
    public void testValidAccountNumber() {
        assertTrue(AccountUtil.validAccountNumber(36241604394L));
        assertFalse(AccountUtil.validAccountNumber(36241604393L));
    }

    @Test
    public void testGyldigFnr() {
        assertTrue(AccountUtil.gyldigFnr("17050355543"));
        assertFalse(AccountUtil.gyldigFnr("45114304788"));
    }
}
