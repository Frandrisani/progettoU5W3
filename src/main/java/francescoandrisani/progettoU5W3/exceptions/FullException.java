package francescoandrisani.progettoU5W3.exceptions;

public class FullException  extends RuntimeException{
    public FullException(Long message) {
        super("Posti pieni");
    }
}
