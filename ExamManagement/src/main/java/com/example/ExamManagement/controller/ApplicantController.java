package com.example.ExamManagement.controller;

import com.example.ExamManagement.ApplicantNotFoundException;
import com.example.ExamManagement.model.Applicant;
import com.example.ExamManagement.model.Level;
import com.example.ExamManagement.repository.ApplicantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
@CrossOrigin
public class ApplicantController {

    private final ApplicantRepository applicantRepository;

    public ApplicantController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @GetMapping("/all")
    public List<Applicant> findAll() {
        ResponseEntity.ok(applicantRepository.findAll());
        return applicantRepository.findAll();

    }

    @GetMapping("/{id}")
    public Applicant findById(@PathVariable long id) {

        //if some condition where the applicant cannot be found
        if ((applicantRepository.findAll()).isEmpty() || (applicantRepository.findAll().get(0).getId().intValue() < 0) || (applicantRepository.findAll().size() <= Math.toIntExact(id)) || applicantRepository.findById(id).isEmpty()) {
            throw new ApplicantNotFoundException("Applicant was not found" + id);
        }


        return applicantRepository.findById(id).get();

    }


    @GetMapping("/name/{surname}")
    public List<Applicant> findBySurname(@PathVariable String surname) {
        return applicantRepository.findAllBySurname(surname);
    }

    @GetMapping("/name/{name}")
    public List<Applicant> findByfName(@PathVariable String name) {
        return applicantRepository.findAllByName
                (name);
    }

    @GetMapping("/level/{level}")
    public List<Applicant> findByLevel(@PathVariable String level) {
        return applicantRepository.findAllByLevel(Level.valueOf(level));

    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addMany")
    public void createMany(@RequestBody List<Applicant> applicants) {
        applicantRepository.saveAll(applicants);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void create(@RequestBody Applicant applicant) {
        applicantRepository.save(applicant);

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Applicant applicant, @PathVariable Long id) {

        if ((applicantRepository.findAll()).isEmpty() || (applicantRepository.findAll().get(0).getId().intValue() < 0) || (applicantRepository.findAll().size() <= Math.toIntExact(id)) || applicantRepository.findById(id).isEmpty()) {
            throw new ApplicantNotFoundException("Applicant was not found" + id);
        }
        applicantRepository.save(applicant);
//        if(!repository.existsById(id)){
//            throw new ApplicantNotFoundException("Applicant was not found." + id);
//        }
//        repository.save(applicant);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if ((applicantRepository.findAll()).isEmpty() || (applicantRepository.findAll().get(0).getId().intValue() < 0) || (applicantRepository.findAll().size() <= Math.toIntExact(id)) || applicantRepository.findById(id).isEmpty()) {
            throw new ApplicantNotFoundException("Applicant was not found" + id);
        }
        applicantRepository.deleteById(id);
    }
//        if(!repository.existsById(id)){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicant was not found.");
//        }
//        repository.delete(id);
//    }

}
