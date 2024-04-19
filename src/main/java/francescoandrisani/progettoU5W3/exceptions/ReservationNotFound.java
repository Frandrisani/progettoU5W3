package francescoandrisani.progettoU5W3.exceptions;

public class ReservationNotFound extends RuntimeException {
    public ReservationNotFound(Long id) {
        super("Reservation not found with ID: " + id);
    }
}
