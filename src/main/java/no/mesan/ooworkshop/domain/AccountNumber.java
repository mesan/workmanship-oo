package no.mesan.ooworkshop.domain;

public class AccountNumber {
    private static final int[] MOD_11_FACTORS = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };

    private final long accountNumber;

    private AccountNumber(final long accountNumber) {
        if (!validAccountNumber(accountNumber)) {
            throw new IllegalArgumentException();
        }
        this.accountNumber = accountNumber;
    }

    /**
     * Opprett forekomst.
     * @param l Kontonummer
     * @return kontonummerobjekt
     * @throws IllegalArgumentException hvis ugyldig
     */
    public static AccountNumber of(long l) throws IllegalArgumentException{
        return new AccountNumber(l);
    }

    @Override
    public String toString() {
        return this.accountNumber + "";
    }

    /**
     * Sjekker om kontonummer har gyldig format
     *
     * @param accountNumber kontonummer
     * @return true hvis gyldig
     */
    private static boolean validAccountNumber(long accountNumber) {
        return accountNumber >= 10_000_000_000L
               && accountNumber <= 99_999_999_999L
               && mod11Check(accountNumber);
    }

    private static boolean mod11Check(long accountNumber) {
        int sum = 0;
        int[] digits = extractDigits(accountNumber);
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * MOD_11_FACTORS[i];
        }

        return 11 - (sum % 11) == digits[10];
    }

    private static int[] extractDigits(long accountNumber) {
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = (int) ((accountNumber % (long) Math.pow(10, 11 - i)) / (long) Math.pow(10, 11 - i - 1));
        }
        return digits;
    }
}
