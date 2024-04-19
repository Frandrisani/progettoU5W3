package francescoandrisani.progettoU5W3.repository;

import francescoandrisani.progettoU5W3.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventDAO extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
}
