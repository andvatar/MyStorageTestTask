package ru.selsup.tarasov.controller;

import org.springframework.web.bind.annotation.*;
import ru.selsup.tarasov.documents.Moving;
import ru.selsup.tarasov.documents.Receipt;
import ru.selsup.tarasov.documents.Selling;
import ru.selsup.tarasov.entity.Document;
import ru.selsup.tarasov.service.DocumentService;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public Document getDocument(@PathVariable int id) {
        return documentService.findById(id);
    }

    @PostMapping("/create/receipt")
    public void createReceiptDocument(@RequestBody Receipt receipt) {
        documentService.createReceipt(receipt);
    }

    @PostMapping("/create/selling")
    public void createSellingDocument(@RequestBody Selling selling) {
        documentService.createSelling(selling);
    }

    @PostMapping("/create/moving")
    public void createMovingDocument(@RequestBody Moving moving) {
        documentService.createMoving(moving);
    }
}
