package francescoandrisani.progettoU5W3.payloads;

import java.time.LocalDateTime;

public record EventResponseDTO(Long id, String title, String description, LocalDateTime dateTime, String location, Integer availableSeats) {
}
