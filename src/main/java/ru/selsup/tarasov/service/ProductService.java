package ru.selsup.tarasov.service;

import org.springframework.stereotype.Service;
import ru.selsup.tarasov.DTO.ProductDTO;
import ru.selsup.tarasov.DTO.ProductMapper;
import ru.selsup.tarasov.DTO.ProductRemainingDTO;
import ru.selsup.tarasov.DTO.ProductRemainingMapping;
import ru.selsup.tarasov.entity.Product;
import ru.selsup.tarasov.entity.StorageProduct;
import ru.selsup.tarasov.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductRemainingMapping productRemainingMapping;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          ProductRemainingMapping productRemainingMapping) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productRemainingMapping = productRemainingMapping;
    }

    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOs;
        productDTOs = productRepository.findAll().stream().map(productMapper::productToProductDTO).toList();
        return productDTOs;
    }

    public ProductDTO findByName(String name) {
        return productMapper.INSTANCE.productToProductDTO(productRepository.findByName(name).orElse(null));
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(()->new NoSuchElementException("Product not found, name = " + name));
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found, id = " + id));
    }

    public List<ProductRemainingDTO> findAllRemaining() {
        return productRepository
                .findAll()
                .stream()
                .map(p -> productRemainingMapping.productToRemainingDTO(p,
                                p.getStorageProducts()
                                .stream()
                                .map(StorageProduct::getQuantity)
                                .mapToInt(Integer::intValue)
                                .sum()))
                .collect(Collectors.toList());
    }

    public List<ProductRemainingDTO> findAllRemainingByStorage(String storageName) {
        return productRepository
                .findAll()
                .stream()
                .map(p -> productRemainingMapping.productToRemainingDTO(p,
                        p.getStorageProducts()
                                .stream()
                                .filter(s -> s.getStorage().getName().equals(storageName))
                                .map(StorageProduct::getQuantity)
                                .mapToInt(Integer::intValue)
                                .sum()))
                .collect(Collectors.toList());
    }

    public void create(Product product) {
        if(product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty, product = " + product);
        }
        if(product.getVendorCode().isEmpty()) {
            throw new IllegalArgumentException("Product vendor code cannot be empty, product = " + product);
        }

        productRepository.save(product);
    }

    public void update(Product product) {
        if(!productRepository.existsById(product.getId())) {
            throw new NoSuchElementException("Product not found, id = " + product.getId());
        }
        if(product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty, product = " + product);
        }
        if(product.getVendorCode().isEmpty()) {
            throw new IllegalArgumentException("Product vendor code cannot be empty, product = " + product);
        }
        productRepository.save(product);
    }

    public void delete(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found, id = " + id));
        if(!product.getStorageProducts().isEmpty()) {
            throw new IllegalStateException("Cannot delete product because it has storage products, product = " + product);
        }
        productRepository.deleteById(id);
    }

}
