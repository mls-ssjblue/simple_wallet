package api.repository;


import api.model.Coin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WalletRepository extends CrudRepository<Coin, Integer> {

    @Override
    public List<Coin> findAll();

}
