package com.app.TrackTheRun.Controllers;

import com.app.TrackTheRun.Dtos.RegisterResponseDTO;
import com.app.TrackTheRun.Dtos.RegisterUserDTO;
import com.app.TrackTheRun.Entities.User;
import com.app.TrackTheRun.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        User user = userService.registerUser(registerUserDTO);

        RegisterResponseDTO res = new RegisterResponseDTO();
        res.setUserName(user.getUserName());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setEmail(user.getEmail());

        return res;
    }
}
