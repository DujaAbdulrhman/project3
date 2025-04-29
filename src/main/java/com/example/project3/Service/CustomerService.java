package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Model.Account;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    

    public void registerCustomer(User userInput, Customer customer) {
        if (userRepository.findUserByUsername(userInput.getUsername()) != null) {
            throw new ApiException("Username already taken");
        }
        User user = new User();
        user.setUsername(userInput.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userInput.getPassword()));
        user.setEmail(userInput.getEmail());
        user.setName(userInput.getName());
        user.setRole("CUSTOMER");

        userRepository.save(user);
        //  ربط الكستمر باليوزر
        Customer customer1 = new Customer();
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setUser(user);
        customerRepository.save(customer1);
    }

    public Customer getMyCustomer(User user) {
        Customer customer = customerRepository.findCustomerByUserId(user.getId());
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }



    public void updateCustomer(User user, Customer newCustomer){
        Customer oldCustomer = customerRepository.findCustomerByUserId(user.getId());
        if (oldCustomer == null){
            throw new ApiException("Customer not found");
        }
        oldCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
        customerRepository.save(oldCustomer);
    }


    public void deleteCustomer(User user){
        Customer customer = customerRepository.findCustomerByUserId(user.getId());
        if (customer == null){
            throw new ApiException("Customer not found");
        }
        customerRepository.delete(customer);
    }


}
