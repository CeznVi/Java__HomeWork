package org.example.springmvc.service;

import org.example.springmvc.models.Shop;
import org.example.springmvc.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop getShopById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    public void saveShop(Shop shop) {
        shopRepository.save(shop);
    }

    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }


    public List<Shop> findBySearchQueryAndCategoryAndAddress(String query, String category, String address) {


        return shopRepository.findBySearchQueryAndCategoryAndAddress(query,category,address);
    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
}
