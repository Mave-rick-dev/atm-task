package com.progressoft.induction.atm.serviceImpl;

import com.progressoft.induction.atm.service.BankingSystem;
import com.progressoft.induction.atm.enums.Banknote;
import com.progressoft.induction.atm.dto.User;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;
import java.math.BigDecimal;
import java.util.Objects;

import static com.progressoft.induction.atm.dto.User.accountRecords;

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
        BigDecimal totalAmount = Banknote.getTotalAmount();
        if(totalAmount.compareTo(withdrawAmount) < 0){
            System.out.println(totalAmount);
            throw new NotEnoughMoneyInATMException();
        }
    }
}
