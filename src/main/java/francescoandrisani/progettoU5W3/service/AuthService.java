package francescoandrisani.progettoU5W3.service;

import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.exceptions.Unauthorized;
import francescoandrisani.progettoU5W3.payloads.LoginRequestDTO;
import francescoandrisani.progettoU5W3.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(LoginRequestDTO payload){
        User user = this.userService.findByEmail(payload.username());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new Unauthorized("Credenziali non valide! Effettua di nuovo il login!");
        }


    }

}
