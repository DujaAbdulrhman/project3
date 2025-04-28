package com.example.project3.Controller;


import com.example.project3.API.ApiResponse;
import com.example.project3.Model.Employee;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create/{userId}")
    public ResponseEntity createEmployee(@PathVariable Integer userId, @RequestBody @Valid Employee employee){
        employeeService.addEmployee(userId, employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added successfully"));
    }

    @GetMapping("/getall")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(employeeService.getEmployeeById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody @Valid Employee employee){
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.status(200).body(new ApiResponse("employee updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body(new ApiResponse("employee deleted successfully"));
    }
}
