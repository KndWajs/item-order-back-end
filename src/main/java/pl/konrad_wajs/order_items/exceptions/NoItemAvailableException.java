package pl.konrad_wajs.order_items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.konrad_wajs.order_items.dto.ItemDTO;

public class NoItemAvailableException extends ResponseStatusException {

    public NoItemAvailableException(ItemDTO item) {
        super(HttpStatus.NOT_FOUND, "No item available in store: " +  item);
    }
}
