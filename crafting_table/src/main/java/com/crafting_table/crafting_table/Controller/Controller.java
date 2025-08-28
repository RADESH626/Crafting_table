package com.crafting_table.crafting_table.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoClient; // Import MongoClient
import com.crafting_table.crafting_table.model.CraftingRequest;

@RestController
public class Controller {

    private final MongoClient mongoClient; // Declare MongoClient field

    // Constructor for dependency injection
    public Controller(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }

    @PostMapping("/craft")
    public String checkCraft( @RequestBody CraftingRequest receta ){

        if(receta==null){
            return("el objeto de la peticion esta vacio");
        }

        String [][] recetaRecibida = receta.getreceta();

        if(recetaRecibida==null){
            return"error al recibir la receta";
        }

        return "receta recibida correctamente";
    }

    // Endpoint to check MongoDB connection status
    @GetMapping("/conexion")
    public String checkConnection() {
        try {
            // Attempt to access the database to verify connection
            mongoClient.getDatabase("Crafting_table");
            return "Conexión exitosa a la base de datos 'Crafting_table'.";
        } catch (Exception e) {
            return "Error de conexión a la base de datos: " + e.getMessage();
        }
    }
}
