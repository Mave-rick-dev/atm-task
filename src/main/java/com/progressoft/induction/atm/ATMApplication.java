package com.progressoft.induction.atm;

import com.google.gson.Gson;
import com.progressoft.induction.atm.serviceImpl.ATMImpl;
import com.progressoft.induction.atm.serviceImpl.BankingSystemImpl;
import java.math.BigDecimal;
import java.util.List;

public class ATMApplication {
    public static void main(String[] args) {
        BankingSystemImpl bankingSystem = new BankingSystemImpl();
        ATMImpl atm = new ATMImpl(bankingSystem);

        List<Banknote> withdraw1 =
                atm.withdraw("123456789", new BigDecimal("1000.0"));
        System.out.println((new Gson().toJson(withdraw1)));

        List<Banknote> withdraw2 =
                atm.withdraw("111111111", new BigDecimal("650.0"));
        System.out.println((new Gson().toJson(withdraw2)));

        List<Banknote> withdraw3 =
                atm.withdraw("222222222", new BigDecimal("650.0"));
        System.out.println((new Gson().toJson(withdraw3)));
    }
}
