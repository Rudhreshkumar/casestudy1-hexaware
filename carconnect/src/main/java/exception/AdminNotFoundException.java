package exception;
import java.io.Serializable;
public class AdminNotFoundException extends Exception  implements Serializable {
	private static final long serialVersionUID = 1L;
    public AdminNotFoundException(String message) {
        super(message);
    }
}
