
public class Random {
	public static int rand(int count, int max) {

		final int PRIME_1 = 2113; // different primes will change the random behaviour
		final int PRIME_2 = 7369; // its generally better to use larger primes
		long time = System.currentTimeMillis() % 1000000;
		long seed = time + count;
		double trig = Math.sin(PRIME_1 * seed + PRIME_2); // value between -1 and 1
		double func = Math.abs(PRIME_1 * trig); // make it positive and larger
		double frac = func - Math.floor(func); // digits past the decimal seem random

		return (int) (frac * max); // get an integer in our range [0, max)
	}
}
