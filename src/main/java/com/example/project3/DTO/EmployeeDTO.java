package com.example.project3.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String position;
    private double salary;

    //عشان اعرف لاي يوزر هو
    private Integer userId;

}
