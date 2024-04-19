package francescoandrisani.progettoU5W3.exceptions;

public class NotFound extends RuntimeException{
    public NotFound(Long id){
        super("Il record con id: " + id + " non è stato trovato!");
    }
    public NotFound(String message){ super(message);}
    }
