package com.example.project3.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class EmployeeDTO {

   @Valid
    @NotNull(message = "User information is required")
    private User user;

    @Valid
    @NotNull(message = "Employee information is required")
    private Employee employee;

}
