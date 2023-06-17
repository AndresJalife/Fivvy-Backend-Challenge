package fivvy.backend_challenge.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling exceptions in controllers and returning a response with the error
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    /**
     * Generates a map with a message
     *
     * @param body the message
     * @return the map
     */
    public static Map<String, String> generateErrorBodyMap(String body) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("error", body);
        return bodyMap;
    }

    /**
     * Handles DisclaimerNotFoundException
     *
     * @param exception the exception to handle
     * @return ResponseEntity with the error
     */
    @ExceptionHandler(DisclaimerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handle(DisclaimerNotFoundException exception) {
        return getNotFoundResponse(exception.getMessage());
    }

    /**
     * Handles IdMissingException
     *
     * @param exception the exception to handle
     * @return ResponseEntity with the error
     */
    @ExceptionHandler(IdMissingException.class)
    public ResponseEntity<Map<String, String>> handle(IdMissingException exception) {
        return getNotFoundResponse(exception.getMessage());
    }

    /**
     * Generates a ResponseEntity with a 404 status and a body with the error
     *
     * @param message the error message
     * @return ResponseEntity with the error
     */
    private ResponseEntity<Map<String, String>> getNotFoundResponse(String message) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(generateErrorBodyMap(message));
    }
}
