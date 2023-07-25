package com.gagejackson.lof.Repositories;

import com.gagejackson.lof.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
