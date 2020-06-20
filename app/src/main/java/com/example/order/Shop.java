package com.example.order;

import java.util.ArrayList;

public class Shop {
    String shopnum;
    Boolean open;
    String name;
    ArrayList<Menu> menus = new ArrayList<Menu>();
    ArrayList<Menu> orders = new ArrayList<Menu>();

    public Shop() {
    }

    public String getShopnum() {
        return shopnum;
    }

    public void setShopnum(String shopnum) {
        this.shopnum = shopnum;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Menu> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Menu> orders) {
        this.orders = orders;
    }

    public void addOrders(Menu menu, int number) {
        for (int i = 0; i < number; i++) {
            orders.add(menu);
        }
    }
}
