import api.exception.InsufficientFundsException;
import api.model.Coin;
import api.repository.WalletRepository;
import api.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class WalletServiceTest {

    private WalletService walletService;

    @MockBean
    private WalletRepository walletRepository;

    @BeforeEach
    public void setup() {
        walletService = new WalletService(walletRepository);
    }

    @Captor
    ArgumentCaptor<List<Coin>> captor;

    @Captor
    ArgumentCaptor<Coin> coinCaptor;

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;

    @Test
    public void should_insert_coins() {
        List<Integer> coinValues = Arrays.asList(1, 2, 3);

        walletService.insertCoins(coinValues);

        List<Coin> expected = getExpectedCoins(coinValues);
        Mockito.verify(walletRepository).saveAll(captor.capture());
        Assertions.assertArrayEquals(expected.toArray(), captor.getValue().toArray());
    }

    private List<Coin> getExpectedCoins(List<Integer> coinValues) {
        return coinValues.stream().map(Coin::new).collect(Collectors.toList());
    }

    @Test
    public void should_get_wallet() {
        List<Integer> coinValues = Arrays.asList(1, 2, 3);
        Mockito.when(walletRepository.findAll()).thenReturn(getExpectedCoins(coinValues));

        List<Integer> wallet = walletService.getWallet();

        assertThat(wallet, equalTo(coinValues));
    }

    @Test
    public void should_handle_exact_amount() {
        List<Integer> coinValues = Arrays.asList(1, 2, 3);
        Mockito.when(walletRepository.findAll()).thenReturn(getExpectedCoins(coinValues));

        List<Integer> wallet = walletService.pay(3);

        verify(walletRepository,times(2)).deleteById(any());
    }

    @Test
    public void should_handle_amount_with_change() {
        List<Integer> coinValues = Arrays.asList(4, 6);
        Mockito.when(walletRepository.findAll()).thenReturn(getExpectedCoins(coinValues));

        List<Integer> wallet = walletService.pay(1);

        verify(walletRepository,times(1)).save(coinCaptor.capture());
        assertThat(coinCaptor.getValue().getValue(),equalTo(3));
    }

    @Test
    public void should_return_error_if_insufficient_funds()  {
        List<Integer> coinValues = Arrays.asList(3);
        Mockito.when(walletRepository.findAll()).thenReturn(getExpectedCoins(coinValues));

        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            walletService.pay(4);
        });

    }

}
