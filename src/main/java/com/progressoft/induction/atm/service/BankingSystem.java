package com.progressoft.induction.atm.service;

import java.math.BigDecimal;

public interface BankingSystem {

    BigDecimal getAccountBalance(String accountNumber);

    void debitAccount(String accountNumber, BigDecimal amount);

    void checkATMBalance(BigDecimal withdrawAmount);
}
