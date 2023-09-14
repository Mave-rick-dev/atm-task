package com.progressoft.induction.atm.serviceImpl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.User;
import java.math.BigDecimal;
import java.util.*;

import static com.progressoft.induction.atm.Banknote.banknoteEnumMap;
import static com.progressoft.induction.atm.User.accountRecords;

public class ATMImpl implements ATM {

    private final BankingSystemImpl bankingSystem;

    public ATMImpl(BankingSystemImpl bankingSystem) {
        this.bankingSystem = bankingSystem;
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal withdrawAmount) {
        BigDecimal availableBalance = bankingSystem.getAccountBalance(accountNumber);

        for(User user: accountRecords){
            if(user.getAccountNumber().equals(accountNumber)){
                availableBalance = user.getAvailableBalance();
            }
        }

        final BigDecimal minimumWithdrawAmount = new BigDecimal("5.0");


        if(withdrawAmount.compareTo(availableBalance) > 0)
            throw new RuntimeException("Insufficient balance!!");

        if(withdrawAmount.compareTo(minimumWithdrawAmount) < 0 && withdrawAmount.remainder(minimumWithdrawAmount).compareTo(BigDecimal.ZERO) != 0)
            throw new RuntimeException("Amount must be greater than 5 and divisible by 5!!");

        ArrayList<Banknote> withdrawBankNotes = new ArrayList<>();
        BigDecimal remainingAmount = withdrawAmount;

        for(Banknote banknote: Banknote.values()){
            while(remainingAmount.compareTo(banknote.getValue()) >= 0 &&  banknoteEnumMap.get(banknote) > 0){
                remainingAmount = remainingAmount.subtract(banknote.getValue());
                banknoteEnumMap.put(banknote, banknoteEnumMap.get(banknote) - 1);
                withdrawBankNotes.add(banknote);
            }
        }

        if(remainingAmount.compareTo(BigDecimal.ZERO) != 0 )
            throw new RuntimeException("Insufficient funds in ATM!!");

        bankingSystem.debitAccount(accountNumber, withdrawAmount);
        return withdrawBankNotes;
    }

}
