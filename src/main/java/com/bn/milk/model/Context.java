package com.bn.milk.model;

public class Context {
    public Order order;
    public Vehicle vehicle;
    public Driver driver;

    public Context(Order order, Vehicle vehicle, Driver driver) {
        this.order = order;
        this.vehicle = vehicle;
        this.driver = driver;
    }
}
