package com.crafting_table.crafting_table.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "items")
public class Item {

    @Id
    String id;

    String outputItem;

    String recipe;

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

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", outputItem=" + outputItem + ", recipe=" + recipe;
    }


}
