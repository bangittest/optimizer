package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

public abstract class BaseCriteria {
    protected double weight;
    protected String name;

    public BaseCriteria(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public abstract double calculate(Order order, Vehicle vehicle, Driver driver);

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getName() { return name; }
}
