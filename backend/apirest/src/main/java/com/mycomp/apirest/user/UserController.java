package com.mycomp.apirest.user;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.apirest.AppConfig;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService _userService;
    private AppConfig _appConfig;

    public UserController(UserService userService, AppConfig appcon) {
      this._userService = userService;
      this._appConfig = appcon;
    }

    @GetMapping("/path")
    public String gettext() {
      return "user";
    }

    @GetMapping("/apiv")
    public String gettext2() {
      return _appConfig.getApiVersion();
    }

    @GetMapping()
    public List<User> getAll() {
      return _userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable Long id) {
      return _userService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) throws BadRequestException {
      try {
        return _userService.create(user);
      } catch (Exception e) {
        throw new BadRequestException(e.getMessage());
      }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(
      @PathVariable Long id,
      @RequestBody User user
    ) throws BadRequestException {
      try {
        _userService.update(id, user);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        throw new BadRequestException(e.getMessage());
      }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
      _userService.remove(id);
    }
}
