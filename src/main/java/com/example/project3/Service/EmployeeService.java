package com.example.project3.Service;
import com.example.project3.API.ApiException;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.EmployeeRepository;
import com.example.project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public void registerEmployee(User userInput, Employee employeeInput) {
        if (userRepository.findUserByUsername(userInput.getUsername()) != null) {
            throw new ApiException("Username already taken");
        }
        User user = new User();
        user.setUsername(userInput.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userInput.getPassword()));
        user.setEmail(userInput.getEmail());
        user.setName(userInput.getName());
        user.setRole("EMPLOYEE");
        userRepository.save(user);

        Employee employee = new Employee();
        employee.setPosition(employeeInput.getPosition());
        employee.setSalary(employeeInput.getSalary());
        employee.setUser(user);
        employeeRepository.save(employee);
    }

    public Employee getMyEmployee(User user) {
        Employee employee = employeeRepository.findEmployeeByUserId(user.getId());
        if (employee == null) {
            throw new ApiException("Employee not found");
        }
        return employee;
    }


    public void updateEmployee(User user, Employee newEmployee){
        Employee oldEmployee = employeeRepository.findEmployeeById(user.getId());
        if (oldEmployee==null){
            throw new ApiException("Employee not found");
        }
        oldEmployee.setPosition(newEmployee.getPosition());
        oldEmployee.setSalary(newEmployee.getSalary());
        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(User user){
        Employee employee = employeeRepository.findEmployeeById(user.getId());
        if (employee==null){
            throw new ApiException("employee not found");
        }
        employeeRepository.delete(employee);
    }
}
