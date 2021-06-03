package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.*;

public class User implements ATM, BankingSystem {

    public static Map<String, BigDecimal> getAccountRecords() {
        Map<String, BigDecimal> accountRecords = new HashMap<>();
        accountRecords.put("123456789", new BigDecimal("1000.0"));
        accountRecords.put("111111111", new BigDecimal("1000.0"));
        accountRecords.put("222222222", new BigDecimal("1000.0"));
        accountRecords.put("333333333", new BigDecimal("1000.0"));
        accountRecords.put("444444444", new BigDecimal("1000.0"));
        return accountRecords;
    }

    public static Map<String, Integer> getBanknoteRecords() {
        Map<String, Integer> banknoteRecords = new HashMap<>();
        banknoteRecords.put("Fifty JOD", 10);
        banknoteRecords.put("Five JOD", 100);
        banknoteRecords.put("Twenty JOD", 20);
        banknoteRecords.put("Ten JOD", 100);
         return banknoteRecords;
    }

    public BigDecimal getTotalAmountInATM() {
        BigDecimal totalAmount = new BigDecimal("0");
        Banknote[] banknoteValues = Banknote.values();

        for (Map.Entry<String, Integer> entry : getBanknoteRecords().entrySet()) {
            String entryKey = entry.getKey();
             Integer entryValue = entry.getValue();
             for (int i = 0; i <= 3; i++) {
                if (entryKey.equalsIgnoreCase(banknoteValues[i].name().replace('_', ' '))){
                    totalAmount = totalAmount.add(BigDecimal.valueOf(entryValue).multiply(banknoteValues[i].getValue()));
                }
            }
        }
        return totalAmount;
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        Banknote[] banknoteValues = Banknote.values();
        List<Banknote> returnedBanknotes = new ArrayList<>();
        if (getAccountRecords().containsKey(accountNumber)) {
            if (amount.compareTo(getTotalAmountInATM()) < 0) {
                if (amount.compareTo(getAccountBalance(accountNumber)) < 0) {
                    debitAccount(accountNumber, amount);
                    do {
                        for (int i = 0; i <= 3; i++) {
                            if (amount.compareTo(banknoteValues[i].getValue()) >= 0 & amount.compareTo(BigDecimal.ZERO) > 0) {
                                returnedBanknotes.add(banknoteValues[i]);
                                amount = amount.subtract(banknoteValues[i].getValue());
                            }
                        }
                    }
                    while (amount.compareTo(BigDecimal.ZERO) > 0);
                } else {
                    throw new InsufficientFundsException();
                }

            } else {
                throw new NotEnoughMoneyInATMException();
            }
        } else {
            throw new AccountNotFoundException();
        }
        return returnedBanknotes;
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        return getAccountRecords().get(accountNumber);
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        BigDecimal oldAmount = getAccountRecords().get(accountNumber);
        BigDecimal newAmount = oldAmount.subtract(amount);
        getAccountRecords().replace(accountNumber, newAmount);
    }

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.withdraw("111111111", new BigDecimal("1000")));
    }
}




