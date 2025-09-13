package com.crafting_table.crafting_table.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.crafting_table.crafting_table.model.CraftingRequest;
import com.crafting_table.crafting_table.model.Item;
import com.crafting_table.crafting_table.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

     @Autowired
     public ItemService(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    public Item findItemByRecipe(String craftingRecipe){

        if(craftingRecipe == null || craftingRecipe.isEmpty() ){

            System.out.println("error al recibir la receta en el service");
            return null;

        }

        Item item = itemRepository.findByRecipe(craftingRecipe);

        if (item == null){

            System.out.println("error al obtener el item de la base de datos en el service");

            return null;
        }

        return item;

    }

    public Item saveItem(Item newitem){

        System.out.println("item recibido:"+ newitem.toString());

        if (newitem == null ){

            System.out.println("error al recibir el nuevo item en el service");
            return null;
        }

        Item itemSaved = itemRepository.save(newitem);

        if (itemSaved==null){

            System.out.println("error al guardar el item en la base de datos en el service");

            return null;

        }

        return itemSaved; 
    } 

}
