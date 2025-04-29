package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    //2. Create a new bank account
    public void createAccount(Integer customerId, Account account){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    //3. Active a bank account
    public void activateAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){
            throw new ApiException("Account not found");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    //4. View account details
    public Account getAccountById(Integer userId, Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){
            throw new ApiException("account not found");
        }
        if (!account.getCustomer().getUser().getId().equals(userId)){
            throw new ApiException("You don't have permission to see this account");
        }
        return account;
    }

    //خليتها سيت عشان اقدر ارجع حسابات العميل بالطريقه ذي
    public Set<Account> getAccountsByUser(Integer userId){
        Customer customer = customerRepository.findCustomerByUserId(userId);
        if (customer == null){
            throw new ApiException("Customer not found");
        }
        return customer.getAccounts();
    }

    // 6. Dposit money
    public void deposit(Integer userId, Integer accountId, double amount){
        if (amount <= 0){
            throw new ApiException("your amount is not positive");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){
            throw new ApiException("Account not found");
        }
        if (!account.getCustomer().getUser().getId().equals(userId)){
            throw new ApiException("you dont have a permission ");
        }
        if (!account.getIsActive()){
            throw new ApiException("account is not active");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    // 6. withdraw money
    public void withdraw(Integer userId, Integer accountId, double amount){
        if (amount <= 0){
            throw new ApiException("Amount must be positive");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){
            throw new ApiException("Account not found");
        }
        if (!account.getCustomer().getUser().getId().equals(userId)){
            throw new ApiException("you dont have a permission ");
        }
        if (!account.getIsActive()){
            throw new ApiException("Account is not active");
        }
        if (account.getBalance() < amount){
            throw new ApiException("insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    // 7. Transfer funds between accounts
    public void transfer(Integer userId, Integer fromAccountId, Integer toAccountId, double amount){
        if (amount <= 0){
            throw new ApiException("Amount must be positive");
        }
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        if (fromAccount==null){
            throw new ApiException("account not found");
        }
        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (toAccount==null){
            throw new ApiException("toAccount not found");
        }

        if (!fromAccount.getCustomer().getUser().getId().equals(userId)){
            throw new ApiException("you dont have a promotion");
        }
        if (!fromAccount.getIsActive() || !toAccount.getIsActive()){
            throw new ApiException("One of the accounts is not active");
        }
        if (fromAccount.getBalance() < amount){
            throw new ApiException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance()- amount);
        toAccount.setBalance(toAccount.getBalance()+amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    // Block bank account
    public void blockAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        if (account==null){
            throw  new ApiException("Account not found");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }
}
