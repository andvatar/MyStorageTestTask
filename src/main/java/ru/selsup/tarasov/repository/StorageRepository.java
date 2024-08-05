package ru.selsup.tarasov.repository;

import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.selsup.tarasov.entity.Storage;

import java.util.Optional;

@Repository("StorageRepository")
@Transactional
public interface StorageRepository  extends JpaRepository<Storage, Integer> {

    @SQL("select * from storage where name = :name")
    Optional<Storage> findByName(@Param("name") String name);
}
