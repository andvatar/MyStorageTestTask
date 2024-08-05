package ru.selsup.tarasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceProductId")
    @SequenceGenerator(name = "SequenceProductId", sequenceName = "product_id_seq", allocationSize = 1)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="vendor_code")
    private String vendorCode;
    @Column(name="last_buying_price")
    private double lastBuyingPrice;
    @Column(name="last_selling_price")
    private double lastSellingPrice;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private List<StorageProduct> storageProducts = new ArrayList<>();

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<StorageProduct> getStorageProducts() {
        return storageProducts;
    }

    public void setStorageProducts(List<StorageProduct> storageProducts) {
        this.storageProducts = storageProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(vendorCode, product.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vendorCode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("vendorCode", vendorCode)
                .append("lastBuyingPrice", lastBuyingPrice)
                .append("lastSellingPrice", lastSellingPrice)
                .append("id", id)
                .toString();
    }
}
