package no.mesan.ooworkshop.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AccountUtil {
    private static final int[] MOD_11_FACTORS = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
    private static final Integer[] MOD_11_FACTORS1 = { 3, 7, 6, 1, 8, 9, 4, 5, 2 };
    private static final Integer[] MOD_11_FACTORS2 = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };

    /** Lengden på gyldig organisasjonsnummer. */
    public static final int LEN_ORGNR = 9;

    /** Lengden på gyldig f.nummer. */
    private static final int LEN_FNR = 11;

    /**
     * Sjekker om kontonummer har gyldig format
     *
     * @param accountNumber kontonummer
     * @return true if gyldig
     */
    public static boolean validAccountNumber(long accountNumber) {
        // Check length
        if (accountNumber < 10_000_000_000L || accountNumber > 99_999_999_999L) {
            return false;
        }

        // Mod11 check
        return mod11Check(accountNumber);
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

    /**
     * Sjekker om f.nummeret har riktig format.
     *
     * @param fnrOrg F.nummer
     * @return <code>true</code> hvis gyldig format
     */
    public static boolean gyldigFnr(final String fnrOrg) {
        if (fnrOrg == null || fnrOrg.trim().length() != LEN_FNR)  return false;
        final List<Integer> fnr = fnrOrg.trim().chars()
                             .map(c -> Character.digit(c, 10))
                             .boxed().collect(Collectors.toList());
        return sjekk(fnr, Arrays.asList(MOD_11_FACTORS1), 0, 0) &&
               sjekk(fnr, Arrays.asList(MOD_11_FACTORS2), 0, 0);
    }

    private static boolean sjekk(final List<Integer> fnr, final List<Integer>fakt,
                                 final int sum, final int index) {
        if ( index==fakt.size() ) {
          int mod11= sum % 11;
          return (mod11==0 && fnr.get(index)==0) || (fnr.get(index)==11-mod11);
        }
        else
          return sjekk(fnr, fakt, sum + (fnr.get(index)*fakt.get(index)), index +1);
    }
}
