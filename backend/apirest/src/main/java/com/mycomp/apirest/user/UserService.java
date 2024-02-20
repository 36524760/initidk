package com.mycomp.apirest.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {
  private UserRepository _userRepo;

  @Autowired
  public UserService(UserRepository userRepo) {
    this._userRepo = userRepo;
  }

  User create(User user) throws Exception {
    var emailExists = _userRepo.emailExists(user.getEmail());
    if (emailExists) {
      System.out.println("email taken");
      throw new Exception("email taken");
    }
    var newUser = _userRepo.save(user);
    return newUser;
  }

  List<User> getAll() {
    return _userRepo.findAll();
  }

  Optional<User> getById(Long id) {
    return _userRepo.findById(id);
  }

  @Transactional
  Optional<User> update(Long id, User user) throws Exception {
    var userToBeUpdated = getById(id);
    if (userToBeUpdated.isEmpty()) {
      throw new Exception("not found");
    }
    var newUser = userToBeUpdated.get();

    newUser.setName(user.getName());
    newUser.setEmail(user.getEmail());

    if (user.getBirthdate() != null) {
      newUser.setBirthdate(user.getBirthdate());
    }
    return Optional.of(newUser);
  }

  void remove(Long id) {
    _userRepo.deleteById(id);
  }
}
