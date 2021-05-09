package api.service;

import api.exception.InsufficientFundsException;
import api.model.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.repository.WalletRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static api.Constants.FAILURE_MESSAGE;

@Service
public class WalletService {

    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public List<Integer> getWallet() {
        List<Coin> coins = repository.findAll();
        return coins.stream().map(Coin::getValue).sorted().collect(Collectors.toList());
    }


    public void insertCoins(List<Integer> coinsValues) {
        List<Coin> coins = coinsValues.stream()
                .map(Coin::new)
                .collect(Collectors.toList());
        repository.saveAll(coins);
    }

    public List<Integer> pay(int amount) {
        List<Coin> coins = repository.findAll();
        List<Coin> updated = new ArrayList<>();
        Collections.sort(coins);
        int index = 0;
        if (coins.size() == 0)
            throw new InsufficientFundsException(FAILURE_MESSAGE + amount, getWallet());
        Coin c = coins.get(index);
        int current = amount;
        while (current > 0) {
            if (c.getValue() >= current) {
                int newValue = c.getValue() - current;
                c.setValue(newValue);
                current = 0;
                updated.add(c);
            } else {
                if (index == coins.size() - 1) {
                    throw new InsufficientFundsException(FAILURE_MESSAGE + amount, getWallet());
                }
                current -= c.getValue();
                c.setValue(0);
                updated.add(c);
                index++;
                c = coins.get(index);
            }
        }
        updateCoinsInWallet(updated);
        return getWallet();
    }

    private List<Integer> getCoinValues(List<Coin> coins) {
        return coins.stream().map(Coin::getValue).collect(Collectors.toList());
    }

    private void updateCoinsInWallet(List<Coin> updated) {
        updated.forEach(c -> {
            if (c.getValue() == 0) {
                repository.deleteById(c.getId());
            } else {
                repository.save(c);
            }
        });
    }
}
