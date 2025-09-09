package com.crafting_table.crafting_table.Controller;

import com.crafting_table.crafting_table.model.CraftingRequest;
import com.crafting_table.crafting_table.model.Ingredient;
import com.crafting_table.crafting_table.model.Recipe;
import com.crafting_table.crafting_table.service.CraftingService; // Assuming a service layer

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.mongodb.client.MongoClient;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private final CraftingService craftingService; // Use a service for logic
    private final MongoClient mongoClient; // Add this line

    // Constructor for dependency injection
    @Autowired // Use Autowired for clarity
    public Controller(CraftingService craftingService, MongoClient mongoClient) {
        this.craftingService = craftingService;
        this.mongoClient = mongoClient; // Assign the injected client
    }

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

    // Endpoint to check MongoDB connection
    @GetMapping("/conexion")
    public String checkConnection() {
        try {
            // Attempt to list database names to verify connection
            List<String> databaseNames = new ArrayList<>();
            mongoClient.listDatabaseNames().into(databaseNames);
            return "MongoDB connection successful. Databases: " + databaseNames.toString();
        } catch (Exception e) {
            return "MongoDB connection failed: " + e.getMessage();
        }
    }

    @PostMapping("/craft")
    public String checkCraft(@RequestBody CraftingRequest RecipeRecivied) { // Keeping parameter name 'receta' as in
                                                                            // original file

        if (RecipeRecivied == null) {
            return "El objeto de la petición está vacío";
        }

        System.out.println(RecipeRecivied.getRecipe());

        String[][] recipe = RecipeRecivied.getRecipe(); // Corrected method name

        System.out.println(recipe);

        if (recipe == null) {
            return "Error al recibir la receta";
        }

        // --- Algorithm Implementation Steps ---
        // 1. Convert String[][] to List<Ingredient> and determine bounding box
        List<Ingredient> ingredients = new ArrayList<>();

        int minRow = 10, maxRow = -1;
        int minCol = 10, maxCol = -1;

        boolean hasIngredients = false;

        for (int r = 0; r < recipe.length; r++) {
            for (int c = 0; c < recipe[r].length; c++) {

                String item = recipe[r][c];

                // System.out.println("item en la pocicion: "+ r + " "+ c + ":" + item);

                if (item != null && !item.trim().isEmpty()) {

                    ingredients.add(new Ingredient(item, r, c));
                    minRow = Math.min(minRow, r);
                    maxRow = Math.max(maxRow, r);
                    minCol = Math.min(minCol, c);
                    maxCol = Math.max(maxCol, c);
                    hasIngredients = true;
                }
            }
        }

        if (!hasIngredients) {
            return "La receta no contiene ingredientes.";
        }

        // 2. Call the service to find the matching recipe

        // The service will handle database lookups and pattern matching
        
        String resultItem = craftingService.findCraftedItem(ingredients, minRow, maxRow, minCol, maxCol);

        System.out.println("item crafteado:"+resultItem);

        if (resultItem != null) {
            return "El item crafteado es: " + resultItem;
        } else {
            return "No se encontró una receta válida para los ingredientes proporcionados.";
        }
    }

    // Removed the /conexion endpoint as MongoClient is no longer directly used in
    // Controller
    // If connection checking is needed, it should be handled via the service or
    // repository layer.
}
