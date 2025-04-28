package com.example.project3.Service;
import com.example.project3.API.ApiException;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.EmployeeRepository;
import com.example.project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public void addEmployee(Integer userId, Employee employee){
        User user = userRepository.findUserById(userId);
               if (user==null){
                   throw new ApiException("User not found");
               }

        employee.setUser(user);
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id){
        return employeeRepository.findEmployeeById(id);
    }


    public void updateEmployee(Integer id, Employee newEmployee){
        Employee oldEmployee = employeeRepository.findEmployeeById(id);
        if (oldEmployee==null){
            throw new ApiException("Employee not found");
        }
        oldEmployee.setPosition(newEmployee.getPosition());
        oldEmployee.setSalary(newEmployee.getSalary());
        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee==null){
            throw new ApiException("employee not found");
        }
        employeeRepository.delete(employee);
    }
}
