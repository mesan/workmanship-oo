package no.mesan.ooworkshop.util;

public class AccountNumberUtil {
	private static final int[] MOD_11_FACTORS = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
	
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
}
