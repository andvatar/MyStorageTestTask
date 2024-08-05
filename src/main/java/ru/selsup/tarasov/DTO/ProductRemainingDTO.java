package ru.selsup.tarasov.DTO;

public class ProductRemainingDTO {
    private String name;
    private String vendorCode;
    private int remainingGoods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getRemainingGoods() {
        return remainingGoods;
    }

    public void setRemainingGoods(int remainingGoods) {
        this.remainingGoods = remainingGoods;
    }
}
