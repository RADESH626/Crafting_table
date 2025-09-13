# Crafting Table Project

## Descripción del Proyecto

este proyecto tienen como propocito principal el recibir recetas de crafteos de minecraft y retornar el item respectivo de esta receta

tambien cuenta con la posibilidad de ingresar nuevas recetas si se desea (por el momento no valida recetas repetidas)


## Tecnologías Utilizadas

- Java
- Spring Boot
- mongo 


## Instalación y Configuración

### Prerrequisitos

- Java Development Kit (JDK) - Se recomienda JDK 17 o superior.
- Maven Wrapper (incluido en el proyecto).

### Pasos de Instalación

1.  Clonar el repositorio:
    ```bash
    git clone https://github.com/RADESH626/Crafting_table.git
    ```
2.  Navegar al directorio del proyecto:
    ```bash
    cd Crafting_table
    ```
3.  El proyecto incluye el Maven Wrapper (`mvnw`), por lo que no es necesario instalar Maven globalmente. Asegúrate de que tu entorno tenga Java Development Kit (JDK) instalado (se recomienda JDK 17 o superior).

## Cómo Ejecutar el Proyecto

1.  Asegúrate de tener el JDK instalado. El wrapper de Maven (`mvnw`) se encargará de la gestión de dependencias de Maven.

2.  Navega al directorio del proyecto:

    ```bash
    cd crafting_table; 
    ```

3. despues de estar en el directorio crafting_table, ejecuta el siguiente comando:

     ```bash
        .\mvnw.cmd spring-boot:run
    ```

    Alternativamente, puedes ejecutar la clase principal `CraftingTableApplication.java` directamente si el IDE lo soporta.

## Uso

### verificar que esta funcionando  

Para verificar que la aplicación está funcionando, realiza una solicitud GET al endpoint `/ping`. Puedes hacerlo usando `curl` o directamente desde tu navegador:

```bash
http://localhost:8080/ping
```

O visita: `http://localhost:8080/ping` en tu navegador. Deberías recibir la respuesta "pong".

## ingresar un item

para ingresar un item a la base de datos , se debe enviar una peticion a la url:

```bash
http://localhost:8080/Item/new
```

usando el metodo POST

en el body de la peticion, se requere guardar los items siguiendo este formato

```bash
{
  "outputItem": "itemName",
  "recipe": "{'itemExample1','itemExample2','itemExample3','itemExample4','itemExample5','itemExample6','itemExample7','itemExample8','itemExample9'}"
}
```

debes remplazar el `itemName` por el nombre del objeto que deseas agregar

tambien debes remplazar los `itemExample` por lo items que requiere el objeto para ser crafteado 

ten en cuenta que cada espacio representa un cuadro de la cuadricula

el primer item de la recipe (`itemExample1`) corresponde al cuadro 1 de la cuadricula de crafteo  

![Crafting_table_inteface](./crafting_table/src/main/resources/static/crafting%20table%20interface.png)

y asi susesivamente 

si el item tiene espacios en blanco en su receta, puedes dejar en blanco los `itemExample` correspondientes


## enviar una receta 

para enviar una receta de crafteo, se tienen que usar el end point /craft enviando una peticion tipo POST

```bash

    http://localhost:8080/craft

```

en el body de la peticion, el formato que debes seguir es este:

```bash

    {
  "recipe": "{'itemExample1','itemExample2','itemExample3','itemExample4','itemExample5','itemExample6','itemExample7','itemExample8','itemExample9'}"
    }

```

recuerda dejar los espacios en blanco si el item que quieres craftear los requiere


