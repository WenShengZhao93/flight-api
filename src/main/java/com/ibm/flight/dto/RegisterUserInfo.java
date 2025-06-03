package com.ibm.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserInfo {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
