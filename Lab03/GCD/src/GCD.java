public class GCD {
    /**
     * Returns a greatest common divisor (GCD) of the given two integers.
     *
     * @param a A number which the GCD is to be computed
     * @param b A number which the GCD is to be computed
     * @return The GCD of a and b
     */
    public static int gcd(int a, int b) {
        if (a == 0) return b;
        else if (b == 0) return a;
        else if (a >= b) return gcd(b, a % b);
        else return gcd(a, b % a);
    }
}