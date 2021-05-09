package api.controller;

import api.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static api.util.Constants.SUCCESS_MESSAGE;

@RestController
public class WalletController {

    Logger logger = LoggerFactory.getLogger(WalletController.class);


    @Autowired
    private WalletService walletService;

    @PostMapping("/insert")
    public ResponseEntity insertCoinsIntoWallet(@RequestBody CoinsRequest coinsRequest) {
        List<Integer> coins = coinsRequest.getCoins();
        walletService.insertCoins(coins);
        logger.info("Coints successfully inserted into wallet");
        return ResponseEntity.ok().build();

    }

    @GetMapping("/getWalletContents")
    public ResponseEntity<List<Integer>> getWalletContents() {
        List<Integer> contents = walletService.getWallet();
        logger.info("Retrieved contents from wallet");
        return ResponseEntity.ok().body(contents);
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> pay(@RequestParam int amount) {
        List<Integer> contents = walletService.pay(amount);
        String message = SUCCESS_MESSAGE + amount;
        PaymentResponse paymentResponse = new PaymentResponse(message, contents);
        logger.info(message);
        return ResponseEntity.ok().body(paymentResponse);
    }


}
