package com.tarsus.example.base.observer;

public class ConcreteObserver implements Observer{
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}
