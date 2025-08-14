package com.crafting_table.crafting_table.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crafting_table.crafting_table.model.CraftingRequest;

@RestController
public class Controller {


        @GetMapping("/ping")
        public String pong(){

            return "pong";
        }

        @PostMapping("/craft")
        public void IterfazCrafteo( @RequestBody CraftingRequest receta ){

            if(receta==null){
                System.out.println("el objeto de la peticion esta vacio");
                System.out.println(receta);
            }

            System.out.println(receta.getreceta());
            String [][] recetaRecibida = receta.getreceta();



            if(recetaRecibida==null){
                System.out.println("error al recibir la receta");
            }else{

                System.out.println("receta recibida correctamente");
            }

           

            // System.out.println("receta valida");
            
        }



}
