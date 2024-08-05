package ru.selsup.tarasov.repository;

import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.selsup.tarasov.entity.Product;

import java.util.Optional;

@Repository("ProductRepository")
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @SQL("select * from product where name = :name")
    Optional<Product> findByName(@Param("name") String name);
}
