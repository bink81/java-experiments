package math;

import java.math.BigInteger;

// This is just a proof of concept. Use library org.apache.commons.lang.math.Fraction instead.
public class Fraction {
	private final BigInteger numerator;
	private final BigInteger denominator;

	public Fraction() {
		this(0);
	}

	public Fraction(long i) {
		this(i, 1);
	}

	public Fraction(long numerator, long denominator) {
		this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
	}

	public Fraction(BigInteger numerator, BigInteger denominator) {
		if (denominator.equals(BigInteger.ZERO)) {
			throw new IllegalArgumentException("You must not divide by zero!");
		}

		// move sign to the numerator
		if (denominator.compareTo(BigInteger.ZERO) < 0) {
			numerator = numerator.negate();
			denominator = denominator.negate();
		}

		// reduce the values if possible
		BigInteger gcd = numerator.gcd(denominator);
		this.numerator = numerator.divide(gcd);
		this.denominator = denominator.divide(gcd);
	}

	public Fraction add(Fraction other) {
		throwExceptionIfNull(other);
		return new Fraction(numerator.multiply(other.denominator).add(other.numerator.multiply(denominator)),
				denominator.multiply(other.denominator));
	}

	public Fraction subtract(Fraction other) {
		throwExceptionIfNull(other);
		return new Fraction(numerator.multiply(other.denominator).subtract(other.numerator.multiply(denominator)),
				denominator.multiply(other.denominator));
	}

	public Fraction divide(Fraction other) {
		throwExceptionIfNull(other);
		return new Fraction(numerator.multiply(other.denominator), denominator.multiply(other.numerator));
	}

	public Fraction multiply(Fraction other) {
		throwExceptionIfNull(other);
		return new Fraction(numerator.multiply(other.numerator), denominator.multiply(other.denominator));
	}

	private void throwExceptionIfNull(Fraction other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter must not be null!");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result + ((numerator == null) ? 0 : numerator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (denominator == null) {
			if (other.denominator != null)
				return false;
		} else if (!denominator.equals(other.denominator))
			return false;
		if (numerator == null) {
			if (other.numerator != null)
				return false;
		} else if (!numerator.equals(other.numerator))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fraction [" + numerator + "/" + denominator + "]";
	}

	public BigInteger getNumerator() {
		return numerator;
	}

	public BigInteger getDenominator() {
		return denominator;
	}
}
