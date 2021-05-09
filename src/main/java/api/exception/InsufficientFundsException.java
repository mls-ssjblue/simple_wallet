package api.exception;

import java.util.List;

public class InsufficientFundsException extends RuntimeException {

    private List<Integer> walletContents;

    public InsufficientFundsException(){
        super();
    }

    public InsufficientFundsException(String message, List<Integer> walletContents){
        super(message);
        this.walletContents = walletContents;
    }

    public List<Integer> getWalletContents() {
        return walletContents;
    }
}
