package com.example.project3.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class AccountDTO {

    private Integer id;
    private String accountNumber;
    private double balance;
    private Boolean isActive;

    //عشان اعرف لاي عميل
    private Integer customerId;
}
