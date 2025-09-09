package com.crafting_table.crafting_table.repository;

import com.crafting_table.crafting_table.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query; // Import Query annotation

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    // Method to find a recipe by its outputItem for idempotency check
    Optional<Recipe> findByOutputItem(String outputItem);

    // New method to find recipes that contain at least one of the specified ingredient names.
    // This query checks the 'itemName' field within the 'ingredients' array.
    @Query("{ 'ingredients.itemName': { $in: ?0 } }")
    List<Recipe> findRecipesWithAnyIngredient(List<String> itemNames);
}
