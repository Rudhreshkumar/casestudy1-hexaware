package exception;
import java.io.Serializable;
public class ReservationException extends Exception  {
	
    public ReservationException(String message) {
        super(message);
    }
}
