package ru.gb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.entity.Category;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    List<Category> findAll();
    Category findById(long id);
}
