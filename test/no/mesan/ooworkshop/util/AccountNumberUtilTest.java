package no.mesan.ooworkshop.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountNumberUtilTest {

	@Test
	public void testValidAccountNumber() {
		assertTrue(AccountNumberUtil.validAccountNumber(36241604394L));
		assertFalse(AccountNumberUtil.validAccountNumber(36241604393L));
	}
}
