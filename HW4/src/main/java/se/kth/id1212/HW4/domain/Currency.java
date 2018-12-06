package se.kth.id1212.HW4.domain;

import javax.persistence.*;

@Entity
public class Currency implements CurrencyDTO{

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "conversionRate")
    private Double conversionRate;


    public Currency() {
        this.name = null;
        this.conversionRate = null;
    }
    public Currency(String name, Double value) {
        this.name = name;
        this.conversionRate = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getConversionRate() {
        return conversionRate;
    }

}
