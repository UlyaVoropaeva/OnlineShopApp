package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Category;
import ru.gb.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/category-all")
    public String findAll(Model model) {
        List<Category> categories = new ArrayList<>();
        repository.findAll().forEach(categories::add);

        model.addAttribute("categories", categories);
        return "/category-all";
    }

    @GetMapping("/category-current/{id}")
    public String findById(@PathVariable() long id, Model model) {
        model.addAttribute("category", repository.findById(id));
        return "/category-current";
    }

    @GetMapping("/category-all/add")
    public String addForm() {
        return "category/category-add";
    }

    @PostMapping
    public String add(@ModelAttribute("addCategories")
                      @RequestBody Category category) {
        repository.save(category);
        return "/category-all";
    }
}
