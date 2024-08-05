package ru.selsup.tarasov.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.selsup.tarasov.documents.DocumentContent;
import ru.selsup.tarasov.documents.Moving;
import ru.selsup.tarasov.documents.Receipt;
import ru.selsup.tarasov.documents.Selling;
import ru.selsup.tarasov.entity.*;
import ru.selsup.tarasov.repository.DocumentRepository;

import java.util.NoSuchElementException;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ProductService productService;
    private final StorageService storageService;
    private final StorageProductService storageProductService;

    public DocumentService(DocumentRepository documentRepository, ProductService productService, StorageService storageService, StorageProductService storageProductService) {
        this.documentRepository = documentRepository;
        this.productService = productService;
        this.storageService = storageService;
        this.storageProductService = storageProductService;
    }

    public Document findById(int id) {
        return documentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Document not found, id =" + id));
    }

    @Transactional
    public void createReceipt(Receipt receipt) {
        saveDocument("receipt", receipt);

        Storage storage = storageService.findByName(receipt.getStorage());
        for(var productReceipt:receipt.getProducts().entrySet()) {
            Product product = productService.findProductByName(productReceipt.getKey());
            int productQuantity = productReceipt.getValue();
            if(productQuantity <= 0) {
                throw new IllegalArgumentException("Invalid product quantity "
                        + product.getName() + ": "
                        + productQuantity);
            }

            StorageProduct storageProduct = storageProductService.getProductStorage(product, storage)
                    .orElse(new StorageProduct(new StorageProductId(product.getId(), storage.getId()),
                            product,
                            storage,
                            0));

            storageProduct.setQuantity(storageProduct.getQuantity() + productQuantity);
            storageProductService.save(storageProduct);
        }
    }

    @Transactional
    public void createSelling(Selling selling) {
        saveDocument("selling", selling);

        Storage storage = storageService.findByName(selling.getStorage());
        for(var productReceipt:selling.getProducts().entrySet()) {
            Product product = productService.findProductByName(productReceipt.getKey());
            int productQuantity = productReceipt.getValue();
            if(productQuantity <= 0) {
                throw new IllegalArgumentException("Invalid product quantity "
                        + product.getName() + ": "
                        + productQuantity);
            }

            StorageProduct storageProduct = storageProductService.getProductStorage(product, storage)
                    .orElseThrow(()-> new NoSuchElementException("No such products on storage "
                            + storage.getName() + ": "
                            + product.getName()));

            if(storageProduct.getQuantity() < productQuantity) {
                throw new IllegalStateException("Not enough products on storage "
                        + storage.getName() + ": "
                        + storageProduct.getQuantity() + " < "
                        + productQuantity);
            }

            storageProduct.setQuantity(storageProduct.getQuantity() - productQuantity);
            storageProductService.save(storageProduct);
        }
    }

    @Transactional
    public void createMoving(Moving moving) {

        saveDocument("moving", moving);

        Storage fromStorage = storageService.findByName(moving.getFromStorage());
        Storage toStorage = storageService.findByName(moving.getToStorage());

        for(var productMoving : moving.getProducts().entrySet()) {
            Product product = productService.findProductByName(productMoving.getKey());
            int productQuantity = productMoving.getValue();
            if(productQuantity <= 0) {
                throw new IllegalArgumentException("Invalid product quantity "
                        + product.getName() + ": "
                        + productQuantity);
            }
            StorageProduct fromStorageProduct = storageProductService.getProductStorage(product, fromStorage)
                    .orElseThrow(() -> new NoSuchElementException("No such products on storage "
                            + fromStorage.getName() + " : "
                            + product.getName()));

            if(fromStorageProduct.getQuantity() < productQuantity) {
                throw new IllegalArgumentException("Not enough products on storage "
                        + fromStorage.getName() + " : "
                        + product.getName() + " : "
                        + productQuantity + " > "
                        + fromStorageProduct.getQuantity());
            }

            StorageProduct toStorageProduct = storageProductService.getProductStorage(product, toStorage)
                    .orElse(new StorageProduct(new StorageProductId(product.getId(), toStorage.getId()),
                            product,
                            toStorage,
                            0));

            fromStorageProduct.setQuantity(fromStorageProduct.getQuantity() - productQuantity);
            toStorageProduct.setQuantity(toStorageProduct.getQuantity() + productQuantity);

            if(fromStorageProduct.getQuantity() == 0) {
                storageProductService.delete(fromStorageProduct);
            }
            else {
                storageProductService.save(fromStorageProduct);
            }
            storageProductService.save(toStorageProduct);

        }
    }

    private void saveDocument(String type, DocumentContent doc) {
        Gson gson = new GsonBuilder().create();
        Document document = new Document(type, gson.toJson(doc));
        documentRepository.save(document);
    }
}
