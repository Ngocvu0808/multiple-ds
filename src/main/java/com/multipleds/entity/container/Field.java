package com.multipleds.entity.container;

import javax.persistence.*;

@Table (name = "field", schema = "base")
public class Field {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "invest")
    private double invest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInvest() {
        return invest;
    }

    public void setInvest(double invest) {
        this.invest = invest;
    }

    public Field(int id, String name, double invest) {
        this.id = id;
        this.name = name;
        this.invest = invest;
    }
}
