package mcrmilenial.appschat.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class RecourceNotFoundExeption extends RuntimeException {
    public RecourceNotFoundExeption(String message) {
        super(message);
    }
    public RecourceNotFoundExeption(String message, Throwable throwable) {
        super(message, throwable);
    }

}
