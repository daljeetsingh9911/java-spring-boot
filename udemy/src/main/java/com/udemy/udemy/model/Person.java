package com.udemy.udemy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // to make the Repository automatic work
@ToString
public class Person {
    @Id
    @GeneratedValue
    private  Long id;

    @Email(message = "Email must be valid") //validation rule
    @NotEmpty(message = "email cannot be empty")
    private  String email;

    @NotEmpty(message = "First name cannot be empty") //validation rule
    private  String first_name;

    @NotEmpty(message = "Last name cannot be empty") //validation rule
    private  String last_name;

    @Past(message = "date must be past date") //validation rule
    private  LocalDate dob;
    private  BigDecimal salary;
}
