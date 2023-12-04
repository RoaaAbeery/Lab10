package com.example.lab.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 4, message = " title length must be more than 4")
    @Column(columnDefinition = "varchar(10) not null ")
    private String title;
    @NotEmpty(message = "description should not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;
    @NotEmpty(message = "location should not be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String location;
    @NotNull(message = "salary should not be null")
    @Positive(message = "salary must be Positive number ")
    @Column(columnDefinition = "int not null")
    private Integer salary;
    @Column(columnDefinition = "DATE")
    private LocalDate postingDate;



}
