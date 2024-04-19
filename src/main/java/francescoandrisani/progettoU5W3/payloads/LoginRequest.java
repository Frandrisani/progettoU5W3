package francescoandrisani.progettoU5W3.payloads;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "ATTENZIONE! L'username è obbligatorio")
        String username,
        @NotEmpty(message = "ATTENZIONE! La password è obbligatoria")
        String password) {
}
