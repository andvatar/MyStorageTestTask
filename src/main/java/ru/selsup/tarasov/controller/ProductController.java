package ru.selsup.tarasov.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.selsup.tarasov.DTO.ProductDTO;
import ru.selsup.tarasov.DTO.ProductRemainingDTO;
import ru.selsup.tarasov.entity.Product;
import ru.selsup.tarasov.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/name/{name}")
    public ProductDTO getProductByName(@PathVariable("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable("id") int id) {
        return productService.findById(id);
    }

    @GetMapping("/remaining")
    public List<ProductRemainingDTO> getRemainingProducts() {
        return productService.findAllRemaining();
    }

    @GetMapping("/remaining/{storage}")
    public List<ProductRemainingDTO> getRemainingProductsByStorage(@PathVariable("storage") String storage) {
        return productService.findAllRemainingByStorage(storage);
    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") int id,
                       @RequestBody Product product) {
        product.setId(id);
        productService.update(product);
    }

    @DeleteMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        productService.delete(id);
    }
}
