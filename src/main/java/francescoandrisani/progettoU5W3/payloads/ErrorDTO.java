package francescoandrisani.progettoU5W3.payloads;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timeError) {
}
