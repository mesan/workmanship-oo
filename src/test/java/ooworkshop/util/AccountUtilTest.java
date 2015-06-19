package ooworkshop.util;

import no.mesan.ooworkshop.util.AccountUtil;
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
        assertTrue(AccountUtil.gyldigFnr("12047566787"));
        assertFalse(AccountUtil.gyldigFnr("12047 566787"));
        assertFalse(AccountUtil.gyldigFnr("24128025489"));
        assertFalse(AccountUtil.gyldigFnr("1404721355"));
        assertFalse(AccountUtil.gyldigFnr("24128025631"));
    }
}
