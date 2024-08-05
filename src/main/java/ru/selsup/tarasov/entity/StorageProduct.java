package ru.selsup.tarasov.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(schema = "public", name = "storage_product")
public class StorageProduct {

    @EmbeddedId
    private StorageProductId storageProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storageId")
    private Storage storage;

    @Column(name = "quantity")
    private int quantity;

    public StorageProduct() {
    }

    public StorageProduct(StorageProductId storageProductId, Product product, Storage storage, int quantity) {
        this.storageProductId = storageProductId;
        this.product = product;
        this.storage = storage;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageProduct that = (StorageProduct) o;
        return Objects.equals(product, that.product) && Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, storage);
    }
}
