package com.app.TrackTheRun.Services;

import com.app.TrackTheRun.Dtos.RegisterUserDTO;
import com.app.TrackTheRun.Entities.User;
import com.app.TrackTheRun.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Tillfällig metod...
    public User registerUser(RegisterUserDTO registerUserDTO) {

        if (registerUserDTO.getUserName() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Användarnamnet behöver vara ifyllt.");
        }

        var userNameExists = userRepository.findByUserName(registerUserDTO.getUserName().toLowerCase());
        if (userNameExists != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Användarnamnet är redan taget!");
        }

        var userEmailExists = userRepository.findByEmail(registerUserDTO.getEmail().toLowerCase());
        if (userEmailExists != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-postadressen är redan registrerat på ett konto.");
        }

        User user = new User();
        user.setUserName(registerUserDTO.getUserName());
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setEmail(registerUserDTO.getEmail());
        user.setCreatedAt(LocalDate.now());

        return userRepository.save(user);
    }
}
