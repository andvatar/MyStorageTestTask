package ru.selsup.tarasov.controller;

import org.springframework.web.bind.annotation.*;
import ru.selsup.tarasov.entity.Storage;
import ru.selsup.tarasov.service.StorageService;

@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{id}")
    public Storage getById(@PathVariable("id") int id) {
        return storageService.findById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody Storage storage) {
        storageService.create(storage);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") int id,
                       @RequestBody Storage storage) {
        storage.setId(id);
        storageService.update(storage);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        storageService.delete(id);
    }
}
