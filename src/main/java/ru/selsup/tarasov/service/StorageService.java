package ru.selsup.tarasov.service;

import org.springframework.stereotype.Service;
import ru.selsup.tarasov.entity.Storage;
import ru.selsup.tarasov.repository.StorageRepository;

import java.util.NoSuchElementException;

@Service
public class StorageService {
    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Storage findById(int id) {
        return storageRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Storage not found, id = " + id));
    }

    public Storage findByName(String name) {
        return storageRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Storage not found, name = " + name));
    }

    public void create(Storage storage) {
        if(storage.getName().isBlank()) {
            throw new IllegalArgumentException("Storage name cannot be blank, storage = " + storage);
        }
        storageRepository.save(storage);
    }

    public void update(Storage storage) {
        if(storage.getName().isBlank()) {
            throw new IllegalArgumentException("Storage name cannot be blank, storage = " + storage);
        }
        if(storageRepository.findById(storage.getId()).isEmpty()) {
            throw new NoSuchElementException("Storage not found, id = " + storage.getId());
        }
        storageRepository.save(storage);
    }

    public void delete(int id) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Storage not found, id = " + id));
        /*if(!storage.getStorageProducts().isEmpty()) {
            throw new IllegalStateException("Storage has products");
        }*/
        storageRepository.deleteById(id);
    }
}
