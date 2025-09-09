# Crafting Table Project

## Descripción del Proyecto

este proyecto tienen como propocito principal el recibir recetas de crafteos de minecraft y retornar el item respectivo de esta receta

## Tecnologías Utilizadas

- Java
- Spring Boot

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
2.  Navega al directorio del proyecto y ejecuta el comando:
    ```bash
    cd crafting_table; .\mvnw.cmd spring-boot:run
    ```
    Alternativamente, puedes ejecutar la clase principal `CraftingTableApplication.java` directamente si el IDE lo soporta.

## Uso

### verificar que esta funcionando  

Para verificar que la aplicación está funcionando, realiza una solicitud GET al endpoint `/ping`. Puedes hacerlo usando `curl` o directamente desde tu navegador:

```bash
http://localhost:8080/ping
```

O visita: `http://localhost:8080/ping` en tu navegador. Deberías recibir la respuesta "pong".

## enviar una receta 

para enviar una receta de crafteo, se tienen que usar el end point /craft enviando una peticion tipo POST

```bash

    http://localhost:8080/craft

```

esta es la estructura de el json que sepera la aplicacion

```bash

    {
  "recipe": [
            ["", "", ""],
            ["", "", ""],
            ["", "",""]
        ]
    }

```

puedes llenar los espacios en blanco con los items que desees

## RECUERDA

el nombre de los items tienen que ser en ingles,

este programa es key sensitive, `ironIngot ` es diferente a `IronIngot `, `Ironingot `, etc 
