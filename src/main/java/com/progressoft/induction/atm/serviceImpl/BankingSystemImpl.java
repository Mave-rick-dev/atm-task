package com.progressoft.induction.atm.serviceImpl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.User;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;
import java.math.BigDecimal;
import java.util.Objects;

import static com.progressoft.induction.atm.User.accountRecords;

public class BankingSystemImpl implements BankingSystem {
    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        BigDecimal availableBalance = null;

        for(User user: accountRecords){
            if(user.getAccountNumber().equals(accountNumber)){
                availableBalance = user.getAvailableBalance();
            }
        }

        if(Objects.isNull(availableBalance))
            throw new AccountNotFoundException();

        return availableBalance;
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        for(User user: accountRecords){
            if(user.getAccountNumber().equals(accountNumber)){
                user.setAvailableBalance(user.getAvailableBalance().subtract(amount));
            }
        }
    }

    @Override
    public void checkATMBalance(BigDecimal withdrawAmount) {
        if(Banknote.getTotalAmount().compareTo(withdrawAmount) < 0)
            throw new NotEnoughMoneyInATMException();
    }
}
