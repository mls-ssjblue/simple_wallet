package api.model;

import lombok.Getter;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
public class Coin implements Comparable<Coin> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int value;

    public Coin(int value) {
        this.value = value;
    }

    public Coin() {
    }

    @Override
    public int compareTo(Coin o) {
        return Integer.compare(value, o.value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return value == coin.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
