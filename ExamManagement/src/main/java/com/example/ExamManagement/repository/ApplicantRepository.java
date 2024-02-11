package com.example.ExamManagement.repository;

import com.example.ExamManagement.model.Applicant;
import com.example.ExamManagement.model.Level;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RepositoryRestResource(path = "applicants")
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {


    List<Applicant> findAllByName(@NotEmpty String name);

    List<Applicant> findAllBySurname(@NotEmpty String surname);

    List<Applicant> findAllByLevel(Level level);

    void deleteAll();


}