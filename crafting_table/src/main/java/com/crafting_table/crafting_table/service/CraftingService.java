package com.crafting_table.crafting_table.service;

import com.crafting_table.crafting_table.model.Ingredient;
import com.crafting_table.crafting_table.model.Recipe;
import com.crafting_table.crafting_table.repository.RecipeRepository; // Assuming a repository for DB interaction
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays; // Import Arrays for array stream and toString

@Service
public class CraftingService {

    private final RecipeRepository recipeRepository; // Dependency for database operations

    @Autowired
    public CraftingService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public String findCraftedItem(List<Ingredient> ingredients, int minRow, int maxRow, int minCol, int maxCol) {

        // System.out.println("valores recividos: ");
        // System.out.println("ingredientes: "+ingredients.size());
        // System.out.println("minRow: "+ minRow);
        // System.out.println("maxRow: " + maxRow );
        // System.out.println("minCol: " + minCol );
        // System.out.println("maxCol: "+ maxCol);



        if (ingredients == null || ingredients.isEmpty()) {
            System.out.println("no se ingresaron ingredientes");
            return null; // No ingredients, no craft
        }

        // Step 1: Extract unique ingredient names from the input for the initial query
        List<String> inputIngredientNames = ingredients.stream()
            .map(Ingredient::getItemName)
            .distinct() // Get unique ingredient names
            .collect(Collectors.toList());

        // Step 2: Fetch candidate recipes using an optimized repository query.
        // This query retrieves recipes that contain AT LEAST ONE of the input ingredient names.
        // This is more efficient than fetching ALL recipes.
        List<Recipe> candidateRecipes = recipeRepository.findRecipesWithAnyIngredient(inputIngredientNames);

        System.out.println("Number of candidate recipes retrieved from DB: " + candidateRecipes.size());

        // Step 3: Filter the candidate recipes based on exact ingredient counts and spatial arrangement.
        // This filtering logic remains in Java as it's complex to translate entirely to a single DB query.
        List<Recipe> matchingRecipes = candidateRecipes.stream().filter(recipe -> {
            
                // Check if the recipe contains all the ingredients from the input
                // This is a complex check: it needs to match ingredient names AND positions.
                // For simplicity here, we'll check if the recipe's ingredients
                // *exactly* match the input ingredients in terms of names and counts.

                // First, check if the number of ingredients matches
                // Use .size() for collections like List
                if (recipe.getIngredients().size() != ingredients.size()) {
                    return false;
                }

                // Create a map of input ingredients for quick lookup
                Map<String, List<Ingredient>> inputIngredientMap = ingredients.stream()
                    .collect(Collectors.groupingBy(Ingredient::getItemName));

                // Check if all ingredients from the recipe are present in the input ingredients
                // and if their counts match.
                Map<String, List<Ingredient>> recipeIngredientMap = recipe.getIngredients().stream()
                    .collect(Collectors.groupingBy(Ingredient::getItemName));

                if (!inputIngredientMap.keySet().equals(recipeIngredientMap.keySet())) {
                    return false; // Different sets of items
                }

                for (Map.Entry<String, List<Ingredient>> entry : inputIngredientMap.entrySet()) {
                    if (!recipeIngredientMap.containsKey(entry.getKey()) ||
                        entry.getValue().size() != recipeIngredientMap.get(entry.getKey()).size()) {
                        return false; // Item missing or count mismatch
                    }
                }

                // Now, check the spatial arrangement.
                // We need to normalize the recipe's ingredients to its own bounding box
                // and compare it to the input recipe's bounding box and ingredient positions.

                // Calculate bounding box for the current recipe from the repository
                int recipeMinRow = Integer.MAX_VALUE, recipeMaxRow = Integer.MIN_VALUE;
                int recipeMinCol = Integer.MAX_VALUE, recipeMaxCol = Integer.MIN_VALUE;
                // Iterate over the list of ingredients
                for (Ingredient ing : recipe.getIngredients()) {
                    recipeMinRow = Math.min(recipeMinRow, ing.getRow());
                    recipeMaxRow = Math.max(recipeMaxRow, ing.getRow());
                    recipeMinCol = Math.min(recipeMinCol, ing.getColumn());
                    recipeMaxCol = Math.max(recipeMaxCol, ing.getColumn());
                }

                // Check if the bounding boxes match in terms of dimensions
                // (maxRow - minRow) should be equal to (recipeMaxRow - recipeMinRow)
                // (maxCol - minCol) should be equal to (recipeMaxCol - recipeMinCol)
                if ((maxRow - minRow) != (recipeMaxRow - recipeMinRow) ||
                    (maxCol - minCol) != (recipeMaxCol - recipeMinCol)) {
                    return false;
                }

                // Check if the relative positions of ingredients match.
                // We can do this by shifting all ingredient positions in the recipe
                // so that its minRow and minCol become 0, and then compare with input.
                // Iterate over the list of ingredients
                for (Ingredient ing : recipe.getIngredients()) {
                    int relativeRow = ing.getRow() - recipeMinRow;
                    int relativeCol = ing.getColumn() - recipeMinCol;

                    // Find the corresponding ingredient in the input list
                    Ingredient inputIngredient = ingredients.stream()
                        .filter(inpIng -> inpIng.getItemName().equals(ing.getItemName()) &&
                                          inpIng.getRow() - minRow == relativeRow &&
                                          inpIng.getColumn() - minCol == relativeCol)
                        .findFirst()
                        .orElse(null);

                    if (inputIngredient == null) {
                        return false; // Ingredient at this relative position doesn't match
                    }
                }

                // If all checks pass, this recipe is a potential match
                return true;
            })
            .collect(Collectors.toList());

        // Step 2: Return the first matching recipe's output item
        if (!matchingRecipes.isEmpty()) {
            // In a real scenario, you might want to handle multiple matches or prioritize.
            // For now, we take the first one.
            return matchingRecipes.get(0).getOutputItem();
        }

        return null; // No match found
    }

}
