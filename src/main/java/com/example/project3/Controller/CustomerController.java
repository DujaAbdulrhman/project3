package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Account;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create/{userId}")
    public ResponseEntity createCustomer(@PathVariable Integer userId, @RequestBody @Valid Customer customer){
        customerService.addCustomer(userId, customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer added successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(customerService.getCustomerById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customer customer){
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

    @GetMapping("/accounts/{customerId}")
    public ResponseEntity<Set<Account>> getCustomerAccounts(@PathVariable Integer customerId){
        return ResponseEntity.status(200).body(customerService.getCustomerAccounts(customerId));
    }
}
