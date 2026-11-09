package ec.edu.espe.productosapirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> noEncontrado(
            ProductoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "codigoEstado", HttpStatus.NOT_FOUND.value()
                ));
    }
    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity<Map<String, Object>> datoInvalido(
            ProductoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "codigoEstado", HttpStatus.BAD_REQUEST.value()
                ));
    }
}
