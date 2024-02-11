package com.example.ExamManagement;

import com.example.ExamManagement.security.AuthService;
import com.example.ExamManagement.security.RegisterRequest;
import com.example.ExamManagement.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class ExamManagementApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ExamManagementApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AuthService authService) {

        return args -> {
            var admin = RegisterRequest.builder().username("God").password("god123").role(Role.ADMIN).build();
            authService.register(admin);
        };
    }
}


//
//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return runner -> {

//createApplicant(applicantDAO);

//	creating a list of applicants
//			createListOfApp(applicantDAO);
//			for(int i =0;i<applicantDAO.findAll().size();i++){
//				System.out.println("Applicants"+applicantDAO.findAll().get(i).getFName()+applicantDAO.findAll().get(i).getLName());
//
//			}

//			for(int i =0;i<applicantDAO.findByLevel(Level.ADVANCED.name()).size();i++){
//				System.out.println("Applicants with advanced level: "+applicantDAO.findByLevel(Level.ADVANCED.name()).get(i).getFName()+" \n"+applicantDAO.findByLevel(Level.ADVANCED.name()).get(i).getLevel());


//        };

			/*
			// Finding by lName or fName
			for (int i=0; i<applicantDAO.findByLName("Doe").size();i++)
				//System.out.println(applicantDAO.findAll().get(i).getFName());
				System.out.println("The Applicants with Last Name is: "+applicantDAO.findByLName("Doe").get(i).getFName() + " LastName: "+applicantDAO.findByLName("Doe").get(i).getLName());

			*/

//		updating an Applicant
//			Applicant applicant = applicantDAO.findById(7L);
//			applicant.setLName("Smith");
//			applicantDAO.update(applicant);

//Delete one applicant
//			applicantDAO.delete(12L);
//			System.out.println(applicantDAO.findAll());
// Deleting all applicants
//System.out.println("Deleted "+ applicantDAO.deleteAll()+" applicants");

//    }


//	private void findApplicant(Long id) {
//		Applicant applicant = applicantDAO.findById(id);
//	}


//	private void createApplicant(ApplicantDAO applicantDAO){
//		Applicant applicant1 = new Applicant("John",
//				"Doe",
//				Calendar.getInstance(),
//				Gender.MALE,
//				Occupation.CIVIL_SERVANT,
//				Nationality.AMERICAN,
//				new Address("streetName",3,"town","district","45231","1" ),
//				"306981234567",
//				"johndoe@example.com",
//				Level.BEGINNER
//		);
//		applicantDAO.save(applicant1);
//		System.out.println("Applicant saved in DB");
//
//		System.out.println(applicant);
//
//
//	}

        /*
    private void createListOfApp(ApplicantDAO applicantDAO) {
        Applicant app1 = new Applicant("John",
                "Doe",
                Calendar.getInstance(),
                Gender.MALE,
                Occupation.CIVIL_SERVANT,
                Nationality.AMERICAN,
                new Address("streetName", 3, "town", "district", "45231", "1"),
                "306981234567",
                "johndoe@example.com",
                Level.BEGINNER);

        Applicant app2 = new Applicant("Ewan",
                "Smith",
                Calendar.getInstance(),
                Gender.MALE,
                Occupation.CIVIL_SERVANT,
                Nationality.AMERICAN,
                new Address("streetName", 3, "town", "district", "45231", "1"),
                "306981234567",
                "johndoe@example.com",
                Level.BEGINNER);

        Applicant app3 = new Applicant("Liam",
                "Carrot",
                Calendar.getInstance(),
                Gender.MALE,
                Occupation.CIVIL_SERVANT,
                Nationality.AMERICAN,
                new Address("streetName", 3, "town", "district", "45231", "1"),
                "306981234567",
                "johndoe@example.com",
                Level.ADVANCED);

        Applicant app4 = new Applicant("Mary",
                "Quinn",
                Calendar.getInstance(),
                Gender.FEMALE,
                Occupation.TEACHER,
                Nationality.ENGLISH,
                new Address("streetName", 3, "town", "district", "45231", "1"),
                "306981234567",
                "maryq@example.com",
                Level.BEGINNER);


        applicantDAO.save(app1);
        applicantDAO.save(app2);
        applicantDAO.save(app3);
        applicantDAO.save(app4);
    }

*/

