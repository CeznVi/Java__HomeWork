package org.example.springmvc.controllers;

import org.example.springmvc.models.Shop;
import org.example.springmvc.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/shops/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("shop", shopService.getShopById(id));
        return "editShop";
    }

    @PostMapping("/shops/{id}/edit")
    public String editShop(@PathVariable("id") Long id, @ModelAttribute Shop editedShop) {
        Shop shopToUpdate = shopService.getShopById(id);
        if (shopToUpdate != null) {
            shopToUpdate.setName(editedShop.getName());
            shopToUpdate.setAddress(editedShop.getAddress());
            shopToUpdate.setPhone(editedShop.getPhone());
            shopToUpdate.setEmail(editedShop.getEmail());
            shopToUpdate.setWebsite(editedShop.getWebsite());
            shopToUpdate.setCategory(editedShop.getCategory());
            shopToUpdate.setDescription(editedShop.getDescription());

            shopService.saveShop(shopToUpdate);
        }
        return "redirect:/shops";
    }

    @GetMapping("/shops/{id}/delete")
    public String deleteShop(@PathVariable("id") Long id) {
        shopService.deleteShopById(id);
        return "redirect:/shops";
    }

    @GetMapping("/search")
    public String searchShops(@RequestParam(value = "query", required = false) String query,
                              @RequestParam(value = "category", required = false) String category,
                              @RequestParam(value = "address", required = false) String address,
                              Model model) {
        List<Shop> foundShops;
        if (query != null && !query.isEmpty()) {
            foundShops = shopService.findBySearchQueryAndCategoryAndAddress(query.toLowerCase(), category.toLowerCase(), address.toLowerCase());
        } else {
            foundShops = shopService.findAll();
        }
        model.addAttribute("shops", foundShops);
        return "shopList";
    }






}
