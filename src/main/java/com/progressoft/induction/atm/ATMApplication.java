package com.progressoft.induction.atm;

import com.progressoft.induction.atm.serviceImpl.ATMImpl;
import com.progressoft.induction.atm.serviceImpl.BankingSystemImpl;

import java.math.BigDecimal;
import java.util.List;

public class ATMApplication {
    public static void main(String[] args) {
        BankingSystemImpl bankingSystem = new BankingSystemImpl();
        ATMImpl atm = new ATMImpl(bankingSystem);

//        List<Banknote> withdraw1 =
//                atm.withdraw("123456789", new BigDecimal("1000.0"));
//        withdraw1.forEach(System.out::println);

        List<Banknote> withdraw2 =
                atm.withdraw("111111111", new BigDecimal("650.0"));
        withdraw2.forEach(System.out::println);

        List<Banknote> withdraw3 =
                atm.withdraw("222222222", new BigDecimal("650.0"));
        withdraw3.forEach(System.out::println);
    }
}
