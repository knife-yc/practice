package com.yc.practice.test.circuitbreaker;

public class Ratio {
    public final int numerator;
    public final int denominator;
    public final double ratio;

    public Ratio(int numerator) {
        this.numerator = numerator;
        this.denominator = numerator;
        this.ratio = 1.0;
    }

    public Ratio(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.ratio = (double) numerator / (double) denominator;
    }

    public Ratio(Ratio rhs) {
        this.numerator = rhs.numerator;
        this.denominator = rhs.denominator;
        this.ratio = rhs.ratio;
    }

    public boolean totally() {
        return this.numerator == this.denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + denominator;
        result = prime * result + numerator;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ratio)) return false;
        Ratio other = (Ratio) obj;
        if (denominator != other.denominator) return false;
        if (numerator != other.numerator) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(numerator);
        builder.append("/").append(denominator);
        builder.append(", ").append(ratio);
        builder.append("]");
        return builder.toString();
    }

    public static Ratio parse(String value) {
        if (null != value && !value.isEmpty()) {
            String[] parts = value.split(",");
            if (null != parts && parts.length > 0) {
                int numerator = 0;
                int denominator = 0;
                try {
                    numerator = Integer.valueOf(parts[0]);
                } catch (Exception e) {
                }
                if (numerator > 0 && parts.length > 1) {
                    try {
                        denominator = Integer.valueOf(parts[1]);
                    } catch (Exception e) {
                    }
                }
                if (denominator > 0) {
                    return new Ratio(numerator, denominator);
                } else {
                    return new Ratio(numerator);
                }
            }
        }
        return null;
    }
}
