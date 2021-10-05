package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Category;
import ru.gb.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Category> categories = new ArrayList<>();
        repository.findAll().forEach(categories::add);

        model.addAttribute("categories", categories);
        return "category/category-all";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable() long id, Model model) {
        model.addAttribute("category", repository.findById(id));
        return "category/category-current";
    }

    @GetMapping("/add")
    public String addForm() {
        return "category/category-add";
    }

    @PostMapping
    public String add(@RequestBody Category category) {
        repository.save(category);
        return "category/category-all";
    }


}
