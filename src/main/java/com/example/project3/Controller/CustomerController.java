package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;



    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody @Valid User user, @RequestBody @Valid Customer customer) {
        customerService.registerCustomer(user, customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }


    @GetMapping("/profile")
    public ResponseEntity<Customer> getMyProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(customerService.getMyCustomer(user));
    }


    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid Customer customer) {
        customerService.updateCustomer(user, customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
        customerService.deleteCustomer(user);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }


    @GetMapping("/my-accounts")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal User user) {
        Set<Account> accounts = accountService.getAccountsByUser(user.getId());
        return ResponseEntity.status(200).body(accounts);
    }

}
