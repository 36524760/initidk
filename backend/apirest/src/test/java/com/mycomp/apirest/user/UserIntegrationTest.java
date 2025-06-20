package com.mycomp.apirest.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;

// @SpringBootTest(classes = {UserRepository.class, UserService.class })
@SpringBootTest()
public class UserIntegrationTest {

  @Autowired
  private UserRepository _userRepo;

  @Autowired
  EntityManager em;

  @Autowired
  private UserService _userService;

  @BeforeEach
  void setUp() {
    _userService = new UserService(_userRepo);
  }

  @Test
  void itShouldUpdateUser() throws Exception {
    var user = new User("a", "a");
    user.setId(Long.valueOf(1));
    user.setBirthdate(LocalDate.now());
    
    var persistedUser = _userService.create(user);

    var toBeUpdated = new User("hello", "hello1");
    var result = _userService.update(persistedUser.getId(), toBeUpdated).get();
    assertEquals(user.getId(), result.getId());
    assertEquals(toBeUpdated.getEmail(), result.getEmail());
  }
}
