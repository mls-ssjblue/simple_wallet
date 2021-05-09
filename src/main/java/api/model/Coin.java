package api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Coin implements Comparable<Coin> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int value;

    public Coin(int value){
        this.value = value;
    }

    public Coin() {}

    @Override
    public int compareTo(Coin o) {
        return Integer.compare(value,o.value);
    }
    public void setValue(int value){
        this.value = value;
    }
}
