package com.example.noteS.User;

import com.example.noteS.CustomError.CustomResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String hashPassword(String password) {
        return encoder.encode(password);
    }

    public Boolean comparePasswords(String hashed, String normal) {
        return encoder.matches(normal, hashed);
    }

    public String check() {
        return "Success";
    }



    public ResponseEntity<?> register(User userData) {
        try {
            if(userData == null) {
                throw new Exception("Invalid data");
            }

            if(userRepository.getUserByEmail(userData.getEmail()) != null) {
                throw new Exception("Email already taken!");
            }

            if(userData.getNickname().length() < 2) {
                throw new Exception("Nickname must be at least 3 letters");
            }

            if(userData.getPassword().length() < 5) {
                throw new Exception("Password too weak");
            }

            userData.setPassword(
                    hashPassword(userData.getPassword())
            );
            return ResponseEntity.ok(userRepository.save(userData));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, e.getMessage()));
        }
    }

    public ResponseEntity<?> login(UserLoginRequest credentials) {
        try {
            if(credentials == null) {
                throw new Exception("Invalid email or password");
            }

            User found_user = userRepository.getUserByEmail(credentials.getEmail());

            if(found_user == null) {
                throw new Exception("Invalid email or password");
            }

            if(comparePasswords(credentials.getPassword(), found_user.getPassword())) {
                throw new Exception("Invalid email or password");
            }

            return ResponseEntity.ok(found_user);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, e.getMessage()));
        }
    }
}
