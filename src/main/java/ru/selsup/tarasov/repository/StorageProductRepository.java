package ru.selsup.tarasov.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.selsup.tarasov.entity.Product;
import ru.selsup.tarasov.entity.StorageProduct;
import ru.selsup.tarasov.entity.StorageProductId;

import java.util.Optional;

@Repository("StorageProductRepository")
@Transactional
public interface StorageProductRepository extends JpaRepository<StorageProduct, StorageProductId> {
    Optional<StorageProduct> findByProductAndStorageId(Product product, int storage_id);
}
