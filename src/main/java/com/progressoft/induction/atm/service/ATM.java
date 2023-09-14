package com.progressoft.induction.atm.service;

import com.progressoft.induction.atm.enums.Banknote;

import java.math.BigDecimal;
import java.util.List;

public interface ATM  {
    List<Banknote> withdraw(String accountNumber, BigDecimal amount);
}
