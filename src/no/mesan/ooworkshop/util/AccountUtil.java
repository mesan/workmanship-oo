package no.mesan.ooworkshop.util;

public class AccountUtil {
	private static final int[] MOD_11_FACTORS = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
	
	/** Lengden på gyldig organisasjonsnummer. */
	public static final int LEN_ORGNR= 9;

	/** Lengden på gyldig fødselsnummer. */
	public static final int LEN_FNR= 11;

	/**
	 * Sjekker om kontonummer har gyldig format
	 * 
	 * @param accountNumber
	 * @return
	 */
	public static boolean validAccountNumber(long accountNumber) {
		//Check length
		if(accountNumber < 10000000000L || accountNumber > 99999999999L) {
			return false;
		}
		
		//Mod11 check
		return mod11Check(accountNumber);
	}
	
	private static boolean mod11Check(long accountNumber) {
		int sum = 0;
		int[] digits = extractDigits(accountNumber);
		for(int i=0; i<10; i++) {
			sum += digits[i] * MOD_11_FACTORS[i];
		}
		
		return 11 - (sum % 11) == digits[10];
	}

	private static int[] extractDigits(long accountNumber) {
		int[] digits = new int[11];
		for(int i=0; i<11; i++) {
			digits[i] = (int) ((accountNumber % (long)Math.pow(10, 11-i)) / (long)Math.pow(10, 11-i-1));
		}
		return digits;
	}
	
	/**
	 * Sjekker om fødselsnummeret har riktig format.
	 *
	 * @param kundenr Fødselsnummer
	 * @return <code>true</code> hvis gyldig format
	 */
	public static boolean gyldigFnr(final String fnrOrg) {
	    if ( fnrOrg == null
	         || fnrOrg.trim().length() != LEN_FNR) {
	        return false;
	    }
	    final String fnr= fnrOrg.trim();
	    int index= 0;
	    int delsum1= 0;
	    int delsum2= 0;
	    final int[] faktor1= new int[] { 3, 7, 6, 1, 8, 9, 4, 5, 2 };
	    final int[] faktor2= new int[] { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
	    while (index < faktor2.length) {
	        final int intverdi= Character.digit(fnr.charAt(index), 10);
	        if (index < faktor1.length) { // Siffer 1-8
	            delsum1+= intverdi * faktor1[index];
	            delsum2+= intverdi * faktor2[index];
	        }
	        else { // Siffer 10
	            final int intmod1= 11 - (delsum1 % 11);
	            if ( !((intmod1 == intverdi) || (intmod1==11 && intverdi==0))) {
	                return false;
	            }
	            delsum2+= intmod1 * faktor2[index];
	        }
	        index++;
	    }
	    final int intverdi2= Character.digit(fnr.charAt(index), 10); // Siffer 11
	    final int intmod2= 11 - (delsum2 % 11);
	    return ((intmod2 == intverdi2) || (intmod2 == 11 && intverdi2 == 0));
	}
}
