package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.domain.Money.MoneyType;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoneyTest {

    @Test
    public void testNormalized() throws Exception {
        assertEquals(100.00D, Money.nok(100).normalized().doubleValue(), 0);
    }

    @Test
    public void addingEqualTypes() throws Exception {
        assertEquals(100.00D, Money.nok(50.0D).add(Money.nok(50.00D)).normalized().doubleValue(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void addingDifferentTypes() throws Exception {
        Money.of(50.0D, MoneyType.EUR).add(Money.nok(50.00D));
    }

    @Test
    public void subtractingEqualTypes() throws Exception {
        assertEquals(10.00D, Money.nok(50.0D).subtract(Money.nok(40.00D)).normalized().doubleValue(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void subtractingDifferentTypes() throws Exception {
        Money.of(50.0D, MoneyType.EUR).subtract(Money.nok(50.00D));
    }

    @Test
    public void comparingEqualTypes() throws Exception {
        final Money b1a = Money.nok(47.11D);
        final Money b1b = Money.nok(47.11D);
        final Money b2 = Money.nok(-114D);
        assertTrue(b1a.gt(b2));
        assertTrue(b1a.ge(b2));
        assertTrue(b1a.ge(b1b));
        assertFalse(b1a.lt(b2));
        assertFalse(b1a.le(b2));
        assertTrue(b1a.le(b1b));
        assertTrue(b1a.equals(b1b));
        assertFalse(b1a.equals(b2));
    }

    @Test(expected = RuntimeException.class)
    public void geBadType() throws Exception {
        Money.nok(47.11D).ge(Money.of(50D, MoneyType.USD));
    }

    @Test(expected = RuntimeException.class)
    public void gtBadType() throws Exception {
        Money.nok(47.11D).gt(Money.of(50D, MoneyType.USD));
    }

    @Test(expected = RuntimeException.class)
    public void leBadType() throws Exception {
        Money.nok(47.11D).le(Money.of(50D, MoneyType.USD));
    }

    @Test(expected = RuntimeException.class)
    public void ltBadType() throws Exception {
        Money.nok(47.11D).lt(Money.of(50D, MoneyType.USD));
    }

    public void equalsBadType() {
        assertFalse(Money.nok(47.11D).equals(Money.of(47.11D, MoneyType.USD)));
    }
}