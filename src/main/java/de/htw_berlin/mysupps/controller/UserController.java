package de.htw_berlin.mysupps.controller;

import de.htw_berlin.mysupps.model.User;
import de.htw_berlin.mysupps.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setAge(updatedUser.getAge());
        user.setAddress(updatedUser.getAddress());
        user.setCity(updatedUser.getCity());
        user.setFitnessGoal(updatedUser.getFitnessGoal());

        return userRepository.save(user);
    }
}
