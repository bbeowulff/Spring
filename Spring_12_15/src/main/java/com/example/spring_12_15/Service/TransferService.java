package com.example.spring_12_15.Service;

import com.example.spring_12_15.Exception.AccountNotFoundException;
import com.example.spring_12_15.Model.Account;
import com.example.spring_12_15.Repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

//    @Transactional
//    public void transferMoney(
//            long idSender,
//            long idReceiver,
//            BigDecimal amount) {
//
//        Account sender = accountRepository.findById(idSender)
//                .orElseThrow(AccountNotFoundException::new);
//
//        Account receiver = accountRepository.findById(idReceiver)
//                .orElseThrow(AccountNotFoundException::new);
//
//        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
//        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
//
//        sender.setAmount(senderNewAmount);
//        receiver.setAmount(receiverNewAmount);
//
//        // JPA will generate UPDATEs for both
//        accountRepository.save(sender);
//        accountRepository.save(receiver);
//    }
@Transactional
public void transferMoney(
        long idSender,
        long idReceiver,
        BigDecimal amount) {

    Account sender = accountRepository.findById(idSender)
    .orElseThrow(() -> new AccountNotFoundException());

    Account receiver = accountRepository.findById(idReceiver)
    .orElseThrow(() -> new AccountNotFoundException());

    BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
    BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

    accountRepository
            .changeAmount((int) idSender, senderNewAmount);
    accountRepository
            .changeAmount((int) idReceiver, receiverNewAmount);
}
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findByName(name);
    }
}
