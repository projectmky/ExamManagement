package com.example.ExamManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "applicant")
public class Applicant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "surname", nullable = false)
    @NotEmpty
    private String surname;

    @Column(name = "reg_Num", unique = true)
    private String reg_Num;

    //@NotEmpty
    @Column(name = "date_of_birth")
    @Basic
    @Temporal(TemporalType.DATE)
    private Calendar dateOfBirth;

    //  @NotEmpty
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //@NotEmpty
    @Column(name = "occupation")
    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    //  @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "nationality")
    private Nationality nationality;

    //@NotEmpty
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "address_id")
    private Address address;

    @NotEmpty
    @Column(name = "mobile")
    private String mobile;

    @NotEmpty
    @Column(name = "email")
    private String email;

    // @NotBlank(message = "Level Empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;



}



