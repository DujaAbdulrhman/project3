package com.example.project3.DTO;


import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String username;
    private String name;
    private String email;
    private String role;
}
