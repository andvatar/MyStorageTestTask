package ru.selsup.tarasov.documents;

import java.util.Map;

public class Receipt  extends DocumentContent{
    private int number;
    private String storage;
    private Map<String, Integer> products;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }
}
