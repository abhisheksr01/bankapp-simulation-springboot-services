package com.service.customer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import jakarta.persistence.*;

@Data
public class CustomerVO {
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "([a-zA-Z',.-]){1,20}", message = "Invalid name")
    private String firstName;

    @NotBlank(message = "Surname cannot be blank")
    @Pattern(regexp = "([a-zA-Z',.-]){1,20}", message = "Invalid surname")
    private String surname;

}
