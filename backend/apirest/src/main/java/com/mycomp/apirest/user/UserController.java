package com.mycomp.apirest.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserRepository _userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
      this._userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getAll() {
      var li = new ArrayList<User>();
      li.add(new User("e"));
      li.add(new User("a" ));
      // return li;
      return _userRepository.findAll();

    }
}
