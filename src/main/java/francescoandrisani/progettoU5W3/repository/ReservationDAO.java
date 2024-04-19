package francescoandrisani.progettoU5W3.repository;

import francescoandrisani.progettoU5W3.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);
}
