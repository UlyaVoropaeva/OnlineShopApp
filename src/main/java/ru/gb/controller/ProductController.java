package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Product;
import ru.gb.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductRepository productService;

    @Autowired
    public ProductController(ProductRepository productService) {
        this.productService = productService;
    }


    @GetMapping()
    public String findAll(Model model) {

        List<Product> products = new ArrayList<>();
        productService.findAll().forEach(products::add);

        model.addAttribute("products", products);
        return "templates/products/product-all";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("products", product);
        return "edit";
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN', 'ROLE_MANAGER'})")
   @PostMapping("/update")
    public String update(@RequestParam Long id,
                         @RequestParam(value = "/edit", required = false) boolean edit) {
        productService.update(id);
        return "redirect:/products/upd";
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN', 'ROLE_MANAGER'})")
    @PostMapping
    public String save(@RequestParam Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-add";
        }
        productService.save(product);
        return "redirect:/products/mvc";
    }


    @GetMapping("/form")
    public String saveForm(Product product) {
        return "product-add";
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }

}