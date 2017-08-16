package org.upv.ccupeiro.contadroid.common.utils;

public class StringUtils {
    private static final String CURRENCY = "€";
    private static final String FORMAT_MONEY = "%.2f " + CURRENCY;

    private StringUtils(){}

    public static String formatAmount(float amount){
        return String.format(FORMAT_MONEY,amount);
    }

}
