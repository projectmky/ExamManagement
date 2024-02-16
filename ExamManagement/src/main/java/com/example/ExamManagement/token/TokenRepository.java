package com.example.ExamManagement.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    // Get all valid tokens belonging to a user
    @Query("""
            select t from Token t inner join User u on t.user.id = u.id where u.id= :userId and (t.isExpired = false or t.isRevoked = false)""")
    List<Token> findAllValidTokenByUser(Integer userId);

    Optional<Token> findByToken(String token);

}
