import java.math.BigInteger;
import java.util.Scanner;

public class IsPrime {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Number to be tested: ");
		long a = sc.nextLong();
		sc.close();
		System.out.println(a + " prime? " + isPrime(a));
	}
	public static boolean isComposite(long a, long n, long oddPart, long deg) {
	      //BigInteger DEG = BigInteger.valueOf(deg);
	      BigInteger ODD_PART = BigInteger.valueOf(oddPart);
	      BigInteger N = BigInteger.valueOf(n);
	      BigInteger A = BigInteger.valueOf(a);
	      
	      BigInteger X = A.modPow(ODD_PART, N);
	      //System.out.println(N.toString() + " " + A.toString() + " " + X.toString() + " " + DEG.toString() + " " + ODD_PART.toString());
	      if (X.longValue() == n-1 || X.longValue() == 1) return false;
	      long tmpDeg = 1;
	      while (tmpDeg < deg) {
	        X = X.modPow(BigInteger.valueOf(2), N);
	        tmpDeg++;
	        if (X.longValue() == n-1) return false;
	        
	      }
	      return true;
	    }
    public static boolean isPrime(long n) { // uses Miller-Rabin algorithm to check if a number if prime
	      if (n < 10) {
	        return n == 2 || n == 3 || n == 5 || n == 7 || ((n & 1) == 0);
	      }
	      long oddPart = n-1, deg = 0;
	      while ((oddPart & 1) == 0) {
	        oddPart = oddPart >> 1;
	        deg++;
	      }
	      
	      long[] randomPrimes = new long[] {2, 3, 5, 7, 11, 17, 113, 149, 911, 991,499927, 999961, 999983, 999_907,999_931,  999_999_929, 999_999_937};
	      
	      for (int i = 0; i < randomPrimes.length; i++) {
	        if (isComposite(2 + (randomPrimes[i] % (n-2)), n, oddPart, deg)) return false;
	      }
	       
	      return true;
    }
}
