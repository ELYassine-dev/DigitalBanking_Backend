package com.digitalbanking.digitalbanking.DTOs;

import com.digitalbanking.digitalbanking.enums.OperationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class OperationsDto {

    private Long id;
    private Date date;
    private Double amount;
    private String description;
    private OperationType operationType;

}
