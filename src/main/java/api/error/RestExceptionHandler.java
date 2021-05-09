package api.error;

import api.controller.PaymentResponse;
import api.exception.InsufficientFundsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    private ResponseEntity handleEntityNotFound(
            InsufficientFundsException ex
    ) {

        PaymentResponse paymentResponse = new PaymentResponse(ex.getMessage(), ex.getWalletContents());
        return buildResponseEntity(paymentResponse);
    }

    private ResponseEntity buildResponseEntity(PaymentResponse paymentResponse) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(paymentResponse);
    }

}