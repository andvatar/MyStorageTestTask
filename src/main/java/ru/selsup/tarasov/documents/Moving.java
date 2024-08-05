package ru.selsup.tarasov.documents;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.selsup.tarasov.utils.ProductQuantityDeserializer;

import java.util.Map;

public class Moving extends DocumentContent{
    private int number;
    private String fromStorage;
    private String toStorage;
    @JsonDeserialize(using = ProductQuantityDeserializer.class)
    private Map<String, Integer> products;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(String fromStorage) {
        this.fromStorage = fromStorage;
    }

    public String getToStorage() {
        return toStorage;
    }

    public void setToStorage(String toStorage) {
        this.toStorage = toStorage;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }
}
