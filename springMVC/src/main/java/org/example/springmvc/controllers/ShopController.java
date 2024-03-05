package org.example.springmvc.controllers;

import org.example.springmvc.models.Shop;
import org.example.springmvc.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public String getAllShops(Model model){
        model.addAttribute("shops", shopService.getAllShops());
        return "shopList";
    }

    @GetMapping("/shops/{id}")
    public String getShopById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("shop", shopService.getShopById(id));
        return "shopDetails";
    }

    @PostMapping("/shops")
    public String addShop(@ModelAttribute Shop shop) {
        shopService.saveShop(shop);
        return "redirect:/shops"; // Перенаправляем на страницу списка магазинов
    }


}
