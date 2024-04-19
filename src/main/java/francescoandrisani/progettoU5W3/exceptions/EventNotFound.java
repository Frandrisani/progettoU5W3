package francescoandrisani.progettoU5W3.exceptions;

public class EventNotFound extends RuntimeException {
    public EventNotFound(Long id) {
        super("Event not found with ID: " + id);
    }
}
