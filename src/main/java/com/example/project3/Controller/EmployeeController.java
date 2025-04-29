package com.example.project3.Controller;


import com.example.project3.API.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeDTO dto) {
        employeeService.registerEmployee(dto.getUser(), dto.getEmployee());
        return ResponseEntity.status(201).body(new ApiResponse("Employee registered successfully"));
    }

    @GetMapping("/profile")
    public ResponseEntity getMyProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(employeeService.getMyEmployee(user));
    }


    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @RequestBody @Valid Employee employee) {
        employeeService.updateEmployee(user, employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user) {
        employeeService.deleteEmployee(user);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }
}
