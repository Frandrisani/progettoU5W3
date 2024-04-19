package francescoandrisani.progettoU5W3.repository;

import francescoandrisani.progettoU5W3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
