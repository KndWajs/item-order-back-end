package pl.konrad_wajs.order_items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmptyRequiredFieldException extends ResponseStatusException {

    public EmptyRequiredFieldException(String field) {
        super(HttpStatus.EXPECTATION_FAILED, field + " can not be empty!");
    }
}
