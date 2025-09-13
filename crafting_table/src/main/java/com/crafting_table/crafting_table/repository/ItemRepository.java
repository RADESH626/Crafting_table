package com.crafting_table.crafting_table.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crafting_table.crafting_table.model.Item;

public interface ItemRepository extends MongoRepository<Item,String>{

    Item findByRecipe(String recipe);

}
