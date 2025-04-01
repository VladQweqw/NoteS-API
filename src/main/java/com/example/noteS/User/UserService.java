package com.example.noteS.User;

import com.example.noteS.CustomError.CustomResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String check() {
        return "Success";
    }


    public ResponseEntity<?> createUser(User userData) {
        try {
            if(userData == null) {
                throw new Exception("Invalid data");
            }

            if(userData.getNickname().length() < 2) {
                throw new Exception("Nickname must be at least 3 letters");
            }

            return ResponseEntity.ok(userRepository.save(userData));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, e.getMessage()));
        }
    }
}
