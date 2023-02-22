package com.farmu.example.common.utils;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.nonNull;

public class BigDecimalUtils {

    public static boolean isPositiveOrZero(final BigDecimal n) {
        return safeBigDecimal(n).compareTo(ZERO) >= 0;
    }

    public static BigDecimal safeBigDecimal(final BigDecimal n) {
        return nonNull(n) ? n : ZERO;
    }

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(ONE_HUNDRED);
    }

    public static BigDecimal percentageSum(BigDecimal a, BigDecimal b, BigDecimal pct){
        BigDecimal a1 = a.add(percentage(a, pct.abs()));
        BigDecimal b1 = b.add(percentage(b, pct.abs()));

        return safe(a1.add(b1));
    }

    public static BigDecimal safe(final BigDecimal d) {
        return nonNull(d) ? d.setScale(2, HALF_UP) : null;
    }

    public static BigDecimal divide(final BigDecimal a, final BigDecimal b, final Integer scale) {
        return safeBigDecimal(a).divide(safeBigDecimal(b), scale, HALF_UP);
    }

    public static BigDecimal divide(final Integer a, final Integer b, final Integer scale) {
        return new BigDecimal(a).divide(new BigDecimal(b), scale, HALF_UP);
    }

    public static BigDecimal subtract(final BigDecimal a, final BigDecimal b) {
        return safeBigDecimal(a).subtract(safeBigDecimal(b));
    }
}
