package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount(@AuthenticationPrincipal User user,
                                                     @RequestBody @Valid Account account) {
        accountService.createAccount(user.getId(), account);
        return ResponseEntity.status(201).body(new ApiResponse("Account created successfully"));
    }

    @PutMapping("/activate/{accountId}")
    public ResponseEntity activateAccount(@PathVariable Integer accountId){
        accountService.activateAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated successfully"));
    }

    @GetMapping("/my-accounts")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.getAccountsByUser(user.getId()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        return ResponseEntity.status(200).body(accountService.getAccountById(user.getId(), accountId));
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable double amount){
        accountService.deposit(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("deposit successful"));
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user,
                                   @PathVariable Integer accountId,
                                   @PathVariable double amount){
        accountService.withdraw(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body( new ApiResponse("withdraw successful"));
    }

    @PutMapping("/transfer/{fromAccountId}/{toAccountId}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user, @PathVariable Integer fromAccountId, @PathVariable Integer toAccountId, @PathVariable double amount){
        accountService.transfer(user.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer successful"));
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked successfully"));
    }
}
