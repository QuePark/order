package com.example.order;

public class Menu {
    String name;
    int price;
    int number;

    public Menu() {
    }

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Menu(String name, int price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void plusNum(){
        number++;
    }

    public int totalPrice(){
        return price*number;
    }
}
