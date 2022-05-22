package rs.njmarko.libraryservice.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBusinessException(HttpServletRequest request, IllegalArgumentException ex) {
        return new ResponseEntity<>("Bad request when trying to access " + request.getRequestURI() + " message: "+ ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
