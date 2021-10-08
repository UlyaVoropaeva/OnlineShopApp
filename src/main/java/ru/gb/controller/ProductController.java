package ru.gb.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Product;
import ru.gb.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductRepository productService;



    @Autowired
    public ProductController(ProductRepository productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public String findAll(@NotNull Model model) {

        List<Product> product = new ArrayList<>();
        productService.findAll().forEach(product::add);
        return "products";

    }

    @GetMapping("/products-add/{id}")
    public String updateProduct (@PathVariable long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);

        return "products-add";
    }

   @PostMapping("/products-add")
    public String update(@RequestParam Long id,
                         @RequestParam(value = "/products", required = false) boolean edit) {
        productService.update(id);
        return "redirect:/products";
    }

    @PostMapping
    public String save(@RequestParam Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "products-add";
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products-add")
    public String saveForm(Model model) {
        model.addAttribute("products", new Product());
        return "products-add";
    }

    @GetMapping("products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}