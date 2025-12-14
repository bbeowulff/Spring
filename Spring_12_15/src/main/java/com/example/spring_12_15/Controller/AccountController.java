package com.example.spring_12_15.Controller;

import com.example.spring_12_15.Model.Account;
import com.example.spring_12_15.Model.TransferRequest;
import com.example.spring_12_15.Service.TransferService;
import org.springframework.web.bind.annotation.*;

import com.example.spring_12_15.Exception.AccountNotFoundException;
import java.util.List;

//@RestController
//public class AccountController {
//
//    private final TransferService transferService;
//
//    public AccountController(TransferService transferService) {
//        this.transferService = transferService;
//    }
//
//    @PostMapping("/transfer")
//    public void transferMoney(
//            @RequestBody TransferRequest request
//    ) {
//        transferService.transferMoney(
//                request.getSenderAccountId(),
//                request.getReceiverAccountId(),
//                request.getAmount());
//    }
//
//    @GetMapping("/accounts")
//    public List<Account> getAllAccounts() {
//        return transferService.getAllAccounts();
//    }
//}
@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(
                                              @RequestBody TransferRequest request
    ) throws AccountNotFoundException {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
                                                           @RequestParam(required = false) String name
    ) {
        if (name == null) {
            return transferService.getAllAccounts();
        } else {
            return transferService.findAccountsByName(name);
        }
    }

}
