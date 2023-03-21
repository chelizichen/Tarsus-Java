package com.tarsus.example.base.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private String message;

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

}
