package com.example.project3.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class CustomerDTO {

    private Integer id;
    private String phoneNumber;

    //عشان اعرف لاي يوزر هو
    private Integer userId;
}
