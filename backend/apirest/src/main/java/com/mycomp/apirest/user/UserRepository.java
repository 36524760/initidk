package com.mycomp.apirest.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // Optional<User> findByEmail(String email);

  @Query("" +
    "SELECT CASE WHEN COUNT(u) > 0 THEN "+
    "TRUE ELSE FALSE END " +
    "FROM User u " +
    "WHERE u.email = ?1"
  )
  Boolean emailExists(String email);

}
