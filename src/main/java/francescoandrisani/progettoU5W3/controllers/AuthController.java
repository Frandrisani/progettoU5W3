package francescoandrisani.progettoU5W3.controllers;

import francescoandrisani.progettoU5W3.exceptions.BadRequest;
import francescoandrisani.progettoU5W3.payloads.LoginRequestDTO;
import francescoandrisani.progettoU5W3.payloads.LoginResponseDTO;
import francescoandrisani.progettoU5W3.payloads.NewUserDTO;
import francescoandrisani.progettoU5W3.payloads.NewUserResponseDTO;
import francescoandrisani.progettoU5W3.service.AuthService;
import francescoandrisani.progettoU5W3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO payload){
        return new LoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequest(validation.getAllErrors());
        }
        return new NewUserResponseDTO(this.userService.save(body).getId());
    }
}
