package com.example.spring_12_15;

import com.example.spring_12_15.Model.Account;
import com.example.spring_12_15.Repository.AccountRepository;
import com.example.spring_12_15.Service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTests {

        @Mock
        private AccountRepository accountRepository;

        @InjectMocks
        private TransferService transferService;
    @Test
    public void moneyTransferHappyFlow() {
        AccountRepository accountRepository =
                mock(AccountRepository.class);
        TransferService transferService =
                new TransferService(accountRepository);

        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account destination = new Account();
        destination.setId(2);
        destination.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(sender.getId()))
                .willReturn(Optional.of(sender));

        given(accountRepository.findById(destination.getId()))
                .willReturn(Optional.of(destination));

        transferService.transferMoney(
                sender.getId(),
                destination.getId(),
                new BigDecimal(100)
        );

        verify(accountRepository)
      .changeAmount(1, new BigDecimal(900));

        verify(accountRepository)
      .changeAmount(2, new BigDecimal(1100));


    }

}
