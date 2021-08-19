package com.example.springtest.entity;

import lombok.*;


import javax.persistence.*;

@Entity
@ToString
@NoArgsConstructor
@Data
@Table(schema = "springhh")
public class Currency {

    @Id
    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_rate")
    private Double currencyRate;

    @Column(name = "currency_name")
    private String currencyName;

    public Currency(String currencyCode, Double currencyRate, String currencyName) {
        this.currencyCode = currencyCode;
        this.currencyRate = currencyRate;
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }


}