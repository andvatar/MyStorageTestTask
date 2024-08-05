package ru.selsup.tarasov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class StorageProductId {
    @Column(name="product_id")
    private int productId;
    @Column(name = "storage_id")
    private int storageId;

    public StorageProductId() {
    }

    public StorageProductId(int productId, int storageId) {
        this.productId = productId;
        this.storageId = storageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }
}
