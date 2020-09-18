package com.egartech.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id; //integer is enough for test project
    private Date date;
    private String companyName;
    private int price;

    public Record() {
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getPrice() {
        return (double) price / 100;
    }

    public String getFormattedPrice() {
        var price = getPrice();
        return String.format("%.2f", price);
    }

    public void setPrice(double price) {
        this.price = (int) (price * 100);
    }
}

