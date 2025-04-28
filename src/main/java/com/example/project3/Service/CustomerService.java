package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Model.Account;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;


    public void addCustomer(Integer userId, Customer customer){
        User user = userRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        customer.setUser(user);
        customerRepository.save(customer);
    }

    public Set<Account> getCustomerAccounts(Integer customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        return customer.getAccounts();
    }

    public Customer getCustomerById(Integer id){
        Customer customer = customerRepository.findCustomerById(id);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    public void updateCustomer(Integer id, Customer newCustomer){
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (oldCustomer==null){
            throw  new ApiException("Customer not found");
        }
        oldCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
        customerRepository.save(oldCustomer);
    }

    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findCustomerById(id);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        customerRepository.delete(customer);
    }

}
