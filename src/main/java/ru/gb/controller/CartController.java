package ru.gb.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Cart;
import ru.gb.entity.Product;
import ru.gb.repository.CartRepository;
import ru.gb.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping()
public class CartController {

    private final CartRepository repository;
    private final ProductRepository repositoryProduct;

    @Autowired
    public CartController(CartRepository repository, ProductRepository repositoryProduct) {
        this.repository = repository;
        this.repositoryProduct = repositoryProduct;
    }

    @GetMapping("/carts")
    public String findAll(@NotNull Model model) {

        List<Cart> carts = new ArrayList<>();
        repository.findAll().forEach(carts::add);
        model.addAttribute("carts", carts);
        return "carts";

    }

    @GetMapping("carts/{id}")
    public Cart findById(@PathVariable Long id) {
        return repository.getById(id);
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/carts";
    }

    @PostMapping("/cartToAdd")
    public String cartToAdd(@ModelAttribute("cartForm")
                            @PathVariable long countProduct, Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "products";
        }
        repository.saveProductToCart(countProduct, product.getId());
        return "redirect:/cards";
    }


    @GetMapping("/cartToAdd")
    public String edit(@ModelAttribute("cartForm") Model model) {
        model.addAttribute("cartForm", new Cart());
        return "cartToAdd";
    }
}
