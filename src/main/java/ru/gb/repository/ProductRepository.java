package ru.gb.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.gb.entity.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
    void deleteById(Long id);
    @Query("SELECT p FROM Product  as p WHERE p.id = :id")
    Product getById(@Param("id") long id);

    @Modifying
    @Query("UPDATE Product as p SET p.name='$name',p.description='$description' where p.id = :id")
    @Transactional
    Product update(long id);
}
