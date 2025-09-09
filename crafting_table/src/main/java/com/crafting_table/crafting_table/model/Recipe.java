package com.crafting_table.crafting_table.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id; // MongoDB generates this automatically

    @Field("outputItem")
    private String outputItem;

    @Field("ingredients")
    private List<Ingredient> ingredients;

    // Constructors
    public Recipe() {
    }

    public Recipe(String outputItem, List<Ingredient> ingredients) {
        this.outputItem = outputItem;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutputItem() {
        return outputItem;
    }

    public void setOutputItem(String outputItem) {
        this.outputItem = outputItem;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
               "id='" + id + '\'' +
               ", outputItem='" + outputItem + '\'' +
               ", ingredients=" + ingredients +
               '}';
    }
}
