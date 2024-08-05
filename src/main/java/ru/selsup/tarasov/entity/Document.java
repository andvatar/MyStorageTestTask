package ru.selsup.tarasov.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "public", name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceDocumentId")
    @SequenceGenerator(name = "SequenceDocumentId", sequenceName = "document_id_seq", allocationSize = 1)
    private int id;
    @Column(name="type")
    private String type;
    @Column(name="document")
    private String document;

    public Document(String type, String document) {
        this.type = type;
        this.document = document;
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
