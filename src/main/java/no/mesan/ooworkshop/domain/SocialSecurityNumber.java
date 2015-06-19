package no.mesan.ooworkshop.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SocialSecurityNumber {

    private static final Integer[] MOD_11_FACTORS1 = { 3, 7, 6, 1, 8, 9, 4, 5, 2 };
    private static final Integer[] MOD_11_FACTORS2 = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
    /** Lengden på gyldig f.nummer. */
    private static final int LEN_FNR = 11;

    private final long ssn;

    private SocialSecurityNumber(final long ssn) {
        if (!validSocialSecurityNumber(Long.toString(ssn))) {
            throw new IllegalArgumentException();
        }
        this.ssn = ssn;
    }

    /**
     * Lag en forekomst.
     * @param ssn fødselsnummer
     * @return SSN
     * @throws IllegalArgumentException hvis ugyldig fødselsnummer
     */
    public static SocialSecurityNumber of(long ssn) throws IllegalArgumentException{
        return new SocialSecurityNumber(ssn);
    }

    @Override
    public String toString() {
        return this.ssn + "";
    }

    /**
     * Sjekker om f.nummeret har riktig format.
     *
     * @param fnrOrg F.nummer
     * @return <code>true</code> hvis gyldig format
     */
    private static boolean validSocialSecurityNumber(final String fnrOrg) {
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
