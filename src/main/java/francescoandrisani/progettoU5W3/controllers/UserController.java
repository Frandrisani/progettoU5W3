package francescoandrisani.progettoU5W3.controllers;

import francescoandrisani.progettoU5W3.entities.Reservation;
import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.payloads.LoginResponseDTO;
import francescoandrisani.progettoU5W3.payloads.NewUserDTO;
import francescoandrisani.progettoU5W3.payloads.NewUserResponseDTO;
import francescoandrisani.progettoU5W3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.userService.getUsers(page, size, sortBy);
    }
    @PostMapping("/register")
    public NewUserResponseDTO createUser(@RequestBody NewUserDTO body) {
        return new NewUserResponseDTO(this.userService.save(body).getId());
    }
}
