package api.controller;

import lombok.Data;

import java.util.List;

@Data
public class PaymentResponse {
    private String message;
    private List<Integer> walletContents;

    public PaymentResponse(String message, List<Integer> walletContents){
        this.message = message;
        this.walletContents = walletContents;
    }

}
