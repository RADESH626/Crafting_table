package com.crafting_table.crafting_table.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Ingredient {

    @Field("itemName")
    private String itemName;

    @Field("row")
    private int row;

    @Field("column")
    private int column;

    // Constructors
    public Ingredient() {
    }

    public Ingredient(String itemName, int row, int column) {
        this.itemName = itemName;
        this.row = row;
        this.column = column;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
               "itemName='" + itemName + '\'' +
               ", row=" + row +
               ", column=" + column +
               '}';
    }
}
