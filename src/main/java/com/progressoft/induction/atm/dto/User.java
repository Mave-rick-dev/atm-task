package com.progressoft.induction.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String accountNumber;
    private BigDecimal availableBalance;
    public static List<User> accountRecords;

    static {
        accountRecords = new ArrayList<>();
        accountRecords.add(new User("123456789", new BigDecimal("1000.0")));
        accountRecords.add(new User("111111111", new BigDecimal("1000.0")));
        accountRecords.add(new User("222222222", new BigDecimal("1000.0")));
        accountRecords.add(new User("333333333", new BigDecimal("1000.0")));
        accountRecords.add(new User("444444444", new BigDecimal("1000.0")));
    }
}
