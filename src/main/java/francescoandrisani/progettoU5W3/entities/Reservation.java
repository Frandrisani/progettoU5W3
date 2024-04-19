package francescoandrisani.progettoU5W3.entities;

import francescoandrisani.progettoU5W3.payloads.ReservationRequestDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Reservation(ReservationRequestDTO body) {
    }
}
