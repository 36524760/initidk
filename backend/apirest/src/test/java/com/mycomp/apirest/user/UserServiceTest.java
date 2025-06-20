package com.mycomp.apirest.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private UserRepository _userRepo;
  @InjectMocks
  private UserService _userService;

  @Test
  void canGetAllUsers() {
    _userService.getAll();

    verify(_userRepo).findAll();
  }

  @Test
  void canCreateUser() throws Exception {
    var user = new User("a", "a");
    _userService.create(user);

    // this method checks if the user repository was passed with the same user
    // object
    ArgumentCaptor<User> ac = ArgumentCaptor.forClass(User.class);
    verify(_userRepo).save(ac.capture());
    User capturedUser = ac.getValue();
    assertThat(capturedUser).isEqualTo(user);
  }

  @Test
  void willThrownEmailIsTaken() throws BadRequestException {
    var user = new User("a", "a");

    when(_userRepo.emailExists(user.getEmail())).thenReturn(true);

    assertThatThrownBy(() -> _userService.create(user))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("email taken");

    // checks if repo.save method is never called
    verify(_userRepo, never()).save(any());
  }

  @Test
  void itShouldThrowNotFoundWhenUpdateWithUserThatDoesntExists() throws Exception {
    var testId = Long.valueOf(1);
    when(_userRepo.findById(any())).thenReturn(Optional.empty());

    assertThatThrownBy(() -> _userService.update(testId, any()))
        .isInstanceOf(Exception.class)
        .hasMessageContaining("not found");
  }

  @Test
  void itShouldUpdateUser() throws Exception {
    var userToBeUpdated = new User("a", "a@");
    userToBeUpdated.setId(Long.valueOf(1));

    var userPassed = new User("e", "e@");

    var expectedUser = new User("e", "e@");
    expectedUser.setId(Long.valueOf(1));

    when(_userRepo.findById(any())).thenReturn(Optional.of(userToBeUpdated));

    var result = _userService.update(userToBeUpdated.getId(), userPassed).get();
    assertEquals(expectedUser.getEmail(), result.getEmail());
    assertEquals(expectedUser.getName(), result.getName());
  }

  @Test
  void itShouldUpdateUserBirthdateIfIsPresent() throws Exception {
    var userToBeUpdated = new User("a", "a@");
    userToBeUpdated.setId(Long.valueOf(1));

    var userPassed = new User("e", "e@");
    userPassed.setBirthdate(LocalDate.of(2023, 3, 20));

    var expectedUser = new User("e", "e@");
    expectedUser.setId(Long.valueOf(1));
    expectedUser.setBirthdate(LocalDate.of(2023, 3, 20));

    when(_userRepo.findById(any())).thenReturn(Optional.of(userToBeUpdated));

    var result = _userService.update(userToBeUpdated.getId(), userPassed).get();
    assertEquals(expectedUser.getBirthdate(), result.getBirthdate());
  }
}
