package org.example.springmvc.repositories;


import org.example.springmvc.models.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("SELECT s FROM Shop s WHERE LOWER(s.name) LIKE %:query% AND LOWER(s.category) LIKE %:category% AND LOWER(s.address) LIKE %:address%")
    List<Shop> findBySearchQueryAndCategoryAndAddress(@Param("query") String query, @Param("category") String category, @Param("address") String address);


}
