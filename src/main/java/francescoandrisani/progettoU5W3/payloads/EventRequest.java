package francescoandrisani.progettoU5W3.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventRequest(
        @NotEmpty(message = "ATTENZIONE! Il titolo è obbligatorio")
        @Size(min = 5, max = 20, message = "Il titolo deve essere compreso tra i 5 e i 20 caratteri")
        String title,
        @NotEmpty(message = "ATTENZIONE! La descrizione è obbligatoria")
        @Size(min = 5, max = 20, message = "L'username deve essere compreso tra i 5 e i 300 caratteri")
        String description,
        @NotEmpty(message = "ATTENZIONE! La data è obbligatoria")
        LocalDateTime dateTime,
        @NotEmpty(message = "ATTENZIONE! Il luogo è obbligatorio")
        String location,
        @NotEmpty(message = "ATTENZIONE! Il numero di posti è obbligatorio")
        Integer posti) {
}
