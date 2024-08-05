package ru.selsup.tarasov.DTO;

public class ProductDTO {
    private String name;
    private String vendorCode;
    private double lastBuyingPrice;
    private double lastSellingPrice;

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

    public double getLastBuyingPrice() {
        return lastBuyingPrice;
    }

    public void setLastBuyingPrice(double lastBuyingPrice) {
        this.lastBuyingPrice = lastBuyingPrice;
    }

    public double getLastSellingPrice() {
        return lastSellingPrice;
    }

    public void setLastSellingPrice(double lastSellingPrice) {
        this.lastSellingPrice = lastSellingPrice;
    }
}
