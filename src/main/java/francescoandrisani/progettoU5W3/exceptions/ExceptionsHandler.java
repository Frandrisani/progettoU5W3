package francescoandrisani.progettoU5W3.exceptions;
import francescoandrisani.progettoU5W3.payloads.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ExceptionsHandler {

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO hendleNotFound(NotFound ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    //Errore generato quando non si ritrova un evento in una ricerca
    @ExceptionHandler(EventNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO eventNotFound(EventNotFound ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    //Errore generato quando non si ritrova una prenotazione in una ricerca
    @ExceptionHandler(EventNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO reservationNotFound(ReservationNotFound ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }


    // Errore generato lato server - Bad Request
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO hendleBadRequest(BadRequest ex){
        if(ex.getErrorList() != null){
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorDTO(message, LocalDateTime.now());

        }else{
            return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
        }

    }

    // Errore generato quando non si è autorizzati tramite token
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(Unauthorized ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }


    // Errore generico
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO genericError(Exception ex){
        ex.printStackTrace();
        return new ErrorDTO("Errore! Il server non risponde - contattare assistenza", LocalDateTime.now());

    }
}
