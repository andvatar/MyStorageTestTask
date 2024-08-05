package ru.selsup.tarasov.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Entity
@Table(schema = "public", name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "storage")
    @JsonIgnore
    private List<StorageProduct> storageProducts = new ArrayList<>();*/


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

    /*public List<StorageProduct> getStorageProducts() {
        return storageProducts;
    }

    public void setStorageProducts(List<StorageProduct> storageProducts) {
        this.storageProducts = storageProducts;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(name, storage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
