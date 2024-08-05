package ru.selsup.tarasov.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.selsup.tarasov.entity.Document;

@Repository("DocumentRepository")
@Transactional
public interface DocumentRepository  extends JpaRepository<Document, Integer> {
}
