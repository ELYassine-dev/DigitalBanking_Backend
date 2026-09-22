package com.digitalbanking.digitalbanking.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqCustomerDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String adresse;


}
