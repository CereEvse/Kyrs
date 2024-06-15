package ru.cereevse.kyrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee")
@Entity(name = "employee")
public class Employee {

    @Id
    @Column(name = "id_employee")
    @GeneratedValue(generator = "id_employee_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id_employee_seq", sequenceName = "id_employee_seq", initialValue = 1, allocationSize = 1)
    private Long idEmployee;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "name_employee")
    private String nameEmployee;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "surname_employee")
    private String surnameEmployee;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "telephone_number_employee")
    private String telNumberEmployee;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
}
