package no.mesan.ooworkshop.domain;

import org.junit.Test;

public class BaseAccountTest  {

    @Test
    public void testValidAccountNumber() throws Exception {
        AccountNumber.of(36241604394L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAccountNumber() throws Exception {
        AccountNumber.of(36241604393L);
    }
}