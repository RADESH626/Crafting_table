package com.crafting_table.crafting_table.Controller;

import com.crafting_table.crafting_table.model.CraftingRequest;
import com.crafting_table.crafting_table.model.Item;
import com.crafting_table.crafting_table.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.mongodb.client.MongoClient;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

@RestController
public class Controller {

    private final ItemService itemservice; // Use a service for logic
    private final MongoClient mongoClient; // Add this line

    // Constructor for dependency injection
    @Autowired // Use Autowired for clarity
    public Controller(ItemService itemservice, MongoClient mongoClient) {
        this.itemservice = itemservice;
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
    public Item checkCraft(@RequestBody CraftingRequest craftingRequest) {

        if (craftingRequest.getRecipe() == null) {
            System.out.println("error al obtener el crafteo en el controller");
            return null;
        }

        System.out.println("receta recibida:" + craftingRequest.getRecipe());

        String craftingRecipe = craftingRequest.getRecipe();

        Item item = itemservice.findItemByRecipe(craftingRecipe);

        if (item == null){
            System.out.println("error al obtener item de la base de datos en el controller");
            return null;
        }

        System.out.println("item encontrado:" + item.toString());

        if (item.getRecipe() == null){

            System.out.println("error al encontrar la receta en el controlador");

            return null;    
        }
    
        return item;

    }

    @PostMapping("/Item/new")
    public Item InsertItem(@RequestBody Item newitem) {

        if( newitem == null ){
            System.out.println("error al recibir el nuevo item");
            return null;
        }

        System.out.println("item recibido en el controller:" + newitem.toString());
        


        Item itemsaved = itemservice.saveItem(newitem);

        if (itemsaved== null){

            System.out.println("error al guardar el item en el controller");

            return null;
        } 

        // System.out.println("item ingresado correctamente");

        return itemsaved;

    }


}