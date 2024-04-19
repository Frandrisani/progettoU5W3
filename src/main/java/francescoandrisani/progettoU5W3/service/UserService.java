package francescoandrisani.progettoU5W3.service;

import francescoandrisani.progettoU5W3.entities.User;
import francescoandrisani.progettoU5W3.exceptions.BadRequest;
import francescoandrisani.progettoU5W3.exceptions.NotFound;
import francescoandrisani.progettoU5W3.payloads.NewUserDTO;
import francescoandrisani.progettoU5W3.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public Page<User> getUsers(int page, int size, String sortBy){
        if(size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.userDAO.findAll(pageable);
    }

    public User save(NewUserDTO body) {
        this.userDAO.findByEmail(body.email()).ifPresent(
                user -> {
                    try {
                        throw new BadRequest("L'email " + user.getEmail() + " è già in uso!");
                    } catch (BadRequest e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        User newUser = new User(body.username(), body.email(), body.password());
        return userDAO.save(newUser);
    }

    public User findById(Long userId){
        return this.userDAO.findById(userId).orElseThrow(() -> new NotFound(userId));
    }

    public User findByIdAndUpdate(Long userId, User modifiedUser){
        User found = this.findById(userId);
        found.setUsername(modifiedUser.getUsername());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        return this.userDAO.save(found);
    }

    public void findByIdAndDelete(Long userId){
        User found = this.findById(userId);
        this.userDAO.delete(found);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFound("Utente con email " + email + " non trovato!"));
    }
}