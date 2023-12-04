package com.example.lab.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name should not be empty")
    @Size(min = 4 ,message = "name length must be more than 4")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(columnDefinition = "varchar(4) not null")
    private String name;
    @Email(message = "please Enter valid Email")
    @Column(columnDefinition = "varchar(20) unique")
    private String email;
    @NotNull(message = " password must be not null")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;
    @NotNull(message = "age should not be empty")
    @Positive(message = "age must be number")
    @Min(value = 21,message = "age must be more than 21")
    @Column(columnDefinition = "int not null CHECK (Age>21)")
    private Integer age;
    @NotNull(message = "role should not be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$")
    @Column(columnDefinition = " varchar(20) CHECK (role = 'JOB_SEEKER' or role = 'EMPLOYER')")
    private String role;
}
