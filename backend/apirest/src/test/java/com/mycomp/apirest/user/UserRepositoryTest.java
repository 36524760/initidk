package com.mycomp.apirest.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
// @Disabled
public class UserRepositoryTest {
  @Autowired
  private UserRepository _userRepo;

  @Autowired
  private TestEntityManager tem;

  @AfterEach
  void tearDown() {
    _userRepo.deleteAll();
  }

  @Test
  void itShouldCheckEmailExists() {
    var email = "a@a.com";
    User user = new User("a", email);
    tem.persist(user);

    var exists = _userRepo.emailExists(email);
    assertThat(exists).isTrue();

  }

  @Test
  void itShouldCheckEmailNotExists() {
    var email = "a@a.com";

    var exists = _userRepo.emailExists(email);
    assertThat(exists).isFalse();
  }
}
