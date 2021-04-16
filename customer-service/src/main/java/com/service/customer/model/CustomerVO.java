package com.service.customer.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CustomerVO {
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "([a-zA-Z',.-]){1,20}", message = "Invalid name")
    private String firstName;

    @NotBlank(message = "Surname cannot be blank")
    @Pattern(regexp = "([a-zA-Z',.-]){1,20}", message = "Invalid surname")
    private String surname;

}
