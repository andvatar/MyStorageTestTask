package ru.selsup.tarasov.service;

import org.springframework.stereotype.Service;
import ru.selsup.tarasov.entity.Product;
import ru.selsup.tarasov.entity.Storage;
import ru.selsup.tarasov.entity.StorageProduct;
import ru.selsup.tarasov.repository.StorageProductRepository;

import java.util.Optional;

@Service
public class StorageProductService {
    private final StorageProductRepository storageProductRepository;

    public StorageProductService(StorageProductRepository storageProductRepository) {
        this.storageProductRepository = storageProductRepository;
    }

    public Optional<StorageProduct> getProductStorage(Product product, Storage storage) {
        return storageProductRepository.findByProductAndStorageId(product, storage.getId());
    }

    public void save(StorageProduct product) {
        storageProductRepository.save(product);
    }

    public void delete(StorageProduct product) {
        storageProductRepository.delete(product);
    }
}
