package com.example.ExamManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "street_Num")
    private Integer street_Num;
    @Column(name = "floor")
    private String floor;
    @Column(name = "town")
    private String town;
    @Column(name = "district")
    private String district;
    @Column(name = "postal_code")
    private String postalCode;

    @JsonManagedReference
    @OneToMany(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Applicant> applicantList;


//    public void add(Applicant applicant) {
//        if (applicant == null) {
//            applicantList = new ArrayList<>();
//
//        }
//        applicantList.add(applicant);
//        if(!applicantList.isEmpty())
//           applicant.setAddress(this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(street_Num, address.street_Num) && Objects.equals(floor, address.floor) && Objects.equals(town, address.town) && Objects.equals(district, address.district) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, street_Num, floor, town, district, postalCode);
    }
}