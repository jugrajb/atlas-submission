package com.atlas.api;

import com.atlas.model.Auth;
import com.atlas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("app/auth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping
  public int login(@Valid @NotNull @RequestBody Auth auth) {
    return authService.login(auth);
  }

  @PostMapping(path = "{username}")
  public int signUp(@PathVariable("username") String username, @Valid @NonNull @RequestBody Auth auth){
    return authService.signUp(auth, username);
  }
}
