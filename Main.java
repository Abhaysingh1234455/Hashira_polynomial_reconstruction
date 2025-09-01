import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static BigInteger convertToDecimal(String value, int base) {
        return new BigInteger(value, base);
    }

    private static BigInteger[] lagrangeInterpolation(int[] x, BigInteger[] y, int k) {
        BigInteger[] coeffs = new BigInteger[k];
        Arrays.fill(coeffs, BigInteger.ZERO);

        List<BigInteger> denominators = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            BigInteger[] Li = new BigInteger[k];
            Arrays.fill(Li, BigInteger.ZERO);
            Li[0] = BigInteger.ONE;

            BigInteger denom = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    denom = denom.multiply(BigInteger.valueOf(x[i] - x[j]));

                    for (int d = k - 1; d >= 1; d--) {
                        Li[d] = Li[d].multiply(BigInteger.valueOf(-x[j])).add(Li[d - 1]);
                    }
                    Li[0] = Li[0].multiply(BigInteger.valueOf(-x[j]));
                }
            }

            denominators.add(denom);

            for (int d = 0; d < k; d++) {
                coeffs[d] = coeffs[d].add(Li[d].multiply(y[i]));
            }
        }

        BigInteger commonDenom = denominators.get(0).abs();
        for (int i = 1; i < denominators.size(); i++) {
            commonDenom = lcm(commonDenom, denominators.get(i).abs());
        }

        for (int i = 0; i < coeffs.length; i++) {
            coeffs[i] = coeffs[i].multiply(commonDenom);
        }

        return coeffs;
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }

        String json = sb.toString();

        int n = Integer.parseInt(json.replaceAll(".*\"n\":\\s*(\\d+).*", "$1"));
        int k = Integer.parseInt(json.replaceAll(".*\"k\":\\s*(\\d+).*", "$1"));

        int[] x = new int[k];
        BigInteger[] y = new BigInteger[k];

        for (int i = 1; i <= k; i++) {
            String entryRegex = "\"" + i + "\"\\s*:\\s*\\{[^}]*\"base\"\\s*:\\s*\"?(\\w+)\"?,\\s*\"value\"\\s*:\\s*\"([^\"]+)\"";
            String entry = json.replaceAll("(?s).*" + entryRegex + ".*", "$1,$2");
            String[] parts = entry.split(",");
            int base = Integer.parseInt(parts[0]);
            String valueStr = parts[1];
            BigInteger val = convertToDecimal(valueStr, base);

            x[i - 1] = i;
            y[i - 1] = val;
        }

        BigInteger[] coeffs = lagrangeInterpolation(x, y, k);

        System.out.print("P(x) = ");
        for (int i = coeffs.length - 1; i >= 0; i--) {
            if (!coeffs[i].equals(BigInteger.ZERO)) {
                if (i < coeffs.length - 1 && coeffs[i].compareTo(BigInteger.ZERO) >= 0) {
                    System.out.print(" + ");
                } else if (coeffs[i].compareTo(BigInteger.ZERO) < 0) {
                    System.out.print(" - ");
                }
                System.out.print(coeffs[i].abs());
                if (i > 0) System.out.print("*x^" + i);
            }
        }
        System.out.println();
    }
}
