package com.progressoft.induction.atm;

import lombok.Getter;
import java.math.BigDecimal;
import java.util.EnumMap;

@Getter
public enum Banknote {

    FIVE_JOD(new BigDecimal("5.0")),
    TEN_JOD(new BigDecimal("10.0")),
    TWENTY_JOD(new BigDecimal("20.0")),
    FIFTY_JOD(new BigDecimal("50.0"));

    private final BigDecimal value;
    public static EnumMap<Banknote, Integer> banknoteEnumMap;

    static {
        banknoteEnumMap = new EnumMap<>(Banknote.class);
        banknoteEnumMap.put(Banknote.FIVE_JOD, 50);
        banknoteEnumMap.put(Banknote.TEN_JOD, 50);
        banknoteEnumMap.put(Banknote.TWENTY_JOD, 20);
        banknoteEnumMap.put(Banknote.FIFTY_JOD, 10);
    }
    Banknote(BigDecimal value) {
        this.value = value;
    }


}


