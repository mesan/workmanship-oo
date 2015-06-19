package no.mesan.ooworkshop.domain;

import java.math.BigDecimal;

public class Money {
    public enum MoneyType {
        NOK(1.00D), EUR(8.50D), USD(9.80D);

        public final BigDecimal rate;

        MoneyType(final double rate) { this.rate= BigDecimal.valueOf(rate); }
    }

    private final BigDecimal value;
    private final MoneyType code;

    private Money(final BigDecimal value, final MoneyType code) {
        this.value = value;
        this.code = code;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code) + " " + this.value;
    }

    public static Money of(final double value, final MoneyType code) {
        return new Money(BigDecimal.valueOf(value), code);
    }

    public static Money nok(final double value) {
        return Money.of(value, MoneyType.NOK);
    }

    public BigDecimal normalized() {
        return this.value.multiply(this.code.rate);
    }

    private void safe(final Money that, String verb, String comp) {
        if (!that.code.equals(this.code)) {
            throw new IllegalArgumentException("cannot " + verb + " " + that.code + " " + comp + " " + this.code);
        }
    }


    public Money add(final Money that) {
        safe(that, "add", "to");
        return new Money(this.value.add(that.value), this.code);
    }

    public Money subtract(final Money that) {
        safe(that, "subtract", "from");
        return new Money(this.value.subtract(that.value), this.code);
    }

    public boolean gt(final Money that) {
        safe(that, "compare", "to");
        return this.value.compareTo(that.value) > 0;
    }

    public boolean ge(final Money that) {
        safe(that, "compare", "to");
        return this.value.compareTo(that.value) >= 0;
    }

    public boolean lt(final Money that) {
        safe(that, "compare", "to");
        return this.value.compareTo(that.value) < 0;
    }

    public boolean le(final Money that) {
        safe(that, "compare", "to");
        return this.value.compareTo(that.value) <= 0;
    }

    @Override
    public boolean equals(final Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        final Money money = (Money) that;
        if (!this.value.equals(money.value)) return false;
        return this.code == money.code;
    }

    @Override
    public int hashCode() {
        return 31 * this.value.hashCode() + this.code.hashCode();
    }
}
