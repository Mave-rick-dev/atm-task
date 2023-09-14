package com.progressoft.induction.atm.serviceImpl;

import com.progressoft.induction.atm.service.ATM;
import com.progressoft.induction.atm.enums.Banknote;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.progressoft.induction.atm.enums.Banknote.banknoteEnumMap;

public class ATMImpl implements ATM {

    private final BankingSystemImpl bankingSystem;

    public ATMImpl(BankingSystemImpl bankingSystem) {
        this.bankingSystem = bankingSystem;
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal withdrawAmount) {
        BigDecimal availableBalance = bankingSystem.getAccountBalance(accountNumber);

        final BigDecimal minimumWithdrawAmount = new BigDecimal("5.0");

        if (withdrawAmount.compareTo(availableBalance) > 0) throw new InsufficientFundsException();

        if (withdrawAmount.compareTo(minimumWithdrawAmount) < 0 && withdrawAmount.remainder(minimumWithdrawAmount).compareTo(BigDecimal.ZERO) != 0)
            throw new RuntimeException("Amount must be greater than 5 and divisible by 5!!");

        bankingSystem.checkATMBalance(withdrawAmount);

        ArrayList<Banknote> withdrawBankNotes = new ArrayList<>();
        BigDecimal remainingAmount = withdrawAmount;

        do {
            for (Banknote banknote : Banknote.values()) {
                if (remainingAmount.compareTo(banknote.getValue()) >= 0 && banknoteEnumMap.get(banknote) > 0) {
                    remainingAmount = remainingAmount.subtract(banknote.getValue());
                    banknoteEnumMap.put(banknote, banknoteEnumMap.get(banknote) - 1);
                    withdrawBankNotes.add(banknote);
                }
            }
        } while (remainingAmount.compareTo(BigDecimal.ZERO) != 0);

        bankingSystem.debitAccount(accountNumber, withdrawAmount);
        return withdrawBankNotes;
    }

}
