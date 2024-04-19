package francescoandrisani.progettoU5W3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public record NewUserDTO(@NotEmpty(message = "ATTENZIONE! L'username è obbligatorio")
                         @Size(min = 5, max = 20, message = "L'username deve essere compreso tra i 5 e i 20 caratteri")
                         String username,
                         @NotEmpty(message = "ATTENZIONE! L'email è obbligatoria")
                         @Email(message = "L'email inserita non è valida")
                         String email,
                         @NotEmpty(message = "ATTENZIONE! La password è obbligatoria")
                         String password
) {
}
