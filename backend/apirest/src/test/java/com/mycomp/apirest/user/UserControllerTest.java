package com.mycomp.apirest.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycomp.apirest.AppConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import java.util.Optional;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  UserService userService;

  @MockBean
  AppConfig appConfig;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void itShouldReturnUserString() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("/users/path");
    MvcResult result = mockMvc.perform(request).andReturn();
    assertEquals("user", result.getResponse().getContentAsString());
  }

  @Test
  void itShouldReturnUserList() throws Exception{
    var users = List.of(
      new User("aaa", "aaa@a.com"),
      new User("a", "e@e.com")
    );
    Mockito.when(userService.getAll()).thenReturn(users);
    mockMvc.perform(get("/users"))
      .andExpect(status().isOk())
      .andExpect(content().json(objectMapper.writeValueAsString(users)));
  }

  @Test 
  void itShouldReceiveAnIdAsLongNumber() throws Exception {
    long testid = 1;
    var user = new User("one", "one@");
    user.setId(testid);
    Mockito.when(userService.getById(Mockito.any())).thenReturn(Optional.of(user));
    MvcResult result = mockMvc.perform(get("/users/" + testid)).andReturn();
    var responseasstring = result.getResponse().getContentAsString();

    var parseObject = objectMapper.readValue(responseasstring, User.class);

    
    assertEquals(testid, parseObject.getId());

    mockMvc.perform(get("/users/" + testid))
      .andExpect(status().isOk())
      .andExpect(content().json(objectMapper.writeValueAsString(user)))
      .andExpect(jsonPath("$.id").value(testid))
      ;
  }
}
