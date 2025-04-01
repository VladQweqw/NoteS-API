package com.example.noteS.User;

import com.example.noteS.CustomError.CustomResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

            userRepository.save(userData);
            return ResponseEntity.ok(new UserDTO(userData));
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

            userRepository.save(found_user);
            return ResponseEntity.ok(new UserDTO(found_user));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, e.getMessage()));
        }
    }

    public ResponseEntity<?> updateUser(String id, String nickname, String email, String password) {
        try {
            Optional<User> user = userRepository.findById(id);

            if(!user.isPresent()) {
                throw new Exception("Invalid user ID");
            }

            if(nickname != null) {
                if(nickname.length() > 2) {
                    user.get().setNickname(nickname);
                }else {
                    throw new Exception("Password must be at least 3 letters long");
                }
            }

            if(email != null) {
                if(email.length() > 2) {
                    user.get().setEmail(email);
                }else {
                    throw new Exception("Email must be at least 3 letters long");
                }
            }

            if(password != null) {
                if(email.length() > 2) {
                    user.get().setPassword(
                            hashPassword(password)
                    );
                }else {
                    throw new Exception("Email must be at least 3 letters long");
                }
            }

            return ResponseEntity.ok(new UserDTO(user.get()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, "Invalid user ID"));
        }
    }

    public ResponseEntity<?> removeUser(String id) {
        try {
            Optional<User> user = userRepository.findById(id);

            if(user.isPresent()) {
                userRepository.delete(user.get());
            }else {
                throw new Exception("Invalid user ID");
            }

            return ResponseEntity.ok("User deleted");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, "Invalid user ID"));
        }
    }
}
