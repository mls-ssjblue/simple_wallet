package api.controller;

import lombok.Getter;

import java.util.List;

@Getter
public class CoinsRequest {
    private List<Integer> coins;

    public CoinsRequest(List<Integer> coins) {
        this.coins = coins;
    }
}
