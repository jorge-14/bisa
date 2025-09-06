RESEÑA

Para el desarrollo de la prueba técnica, se implementó una arquitectura basada en Spring Boot, que facilita la creación y gestión de servicios y APIs RESTful. A continuación, se describen las principales herramientas y tecnologías empleadas:
1. Lenguaje de programación: Java 17
1. Framework principal: Spring Boot versión 3.5.5
1. Gestor de dependencias: Maven versión 3.3.1
1. Base de datos: H2 (base de datos en memoria)
1. Frameworks y Librerías Adicionales:
- Spring Web: Utilizado para la creación de servicios RESTful.
- Spring Data JPA: Permite la gestión de la persistencia de datos utilizando el patrón Repository.
- Lombok: Reduce la escritura de código repetitivo como getters, setters, constructores, wntre otros

Estructura de la Solución
La solución fue organizada siguiendo una arquitectura en capas, lo que permite una mejor separación de responsabilidades y mayor mantenibilidad del código. Las capas principales son:
Controller: Encargados de manejar las solicitudes HTTP entrantes y retornar las respuestas correspondientes.
Service / ServiceImpl: Contienen la lógica de negocio. La interfaz Service define los métodos disponibles, mientras que ServiceImpl proporciona su implementación.
Repository: Interfaces que extienden de JpaRepository, permitiendo la interacción con la base de datos de forma simple y eficiente.
Modelos / Entidades: Representan las estructuras de datos persistentes en la base de datos.

Descripción de APIS realizadas  

Módulo: Autor  
Crear Autor  
URL: POST http://localhost:8080/api/v1/author/create-author  
Descripción: Crea un nuevo autor en el sistema.  
Body de ejemplo:  
{  
  "name": "Jorge Luis",  
  "paternalSurname": "Choque",  
  "maternalSurname": "Callizaya",  
  "birthdate": "1997-12-08",  
  "email": " jorgeluischoquecallizaya@gmail.com",  
  "country": "Bolivia"  
}  

Listar Todos los Autores  
URL: GET http://localhost:8080/api/v1/author/lis-all-author  
Descripción: Devuelve la lista completa de autores registrados.  

Listar Autor con Blogs y Comentarios  
URL: GET http://localhost:8080/api/v1/author/lis-information-author-blog-comment  
Descripción: Devuelve la información de cada autor junto con sus blogs y los comentarios asociados a esos blogs.  

Módulo: Blog  
Crear Blog  
URL: POST http://localhost:8080/api/v1/blog/create-blog/{idAutor}  
Parámetro en la URL: idAutor – ID del autor al que pertenece el blog.  
Nota: Para la Periodicidad se utilizó un ENUM con las palabras en inglés DAILY, WEEKLY, MONTHLY  
Body de ejemplo:  
{  
    "title": "Nuevo Blog",  
    "periodicity": "DAILY",  
    "comment": true,  
    "idAuthor": 1,  
    "content": {  
        "text": "Nueva colección ",  
        "image": "imagem.jpg"  
    }  
}  
Actualizar Blog  
URL: PUT http://localhost:8080/api/v1/blog/update-blog/{idBlog}  
Parámetro en la URL: idBlog – ID del blog a actualizar.  
Body de ejemplo:  
{  
    "title": "Literatura Nueva",  
    "periodicity": "MONTHLY",  
    "comment": false,  
    "content": {  
        "text": "Buen Blog",  
        "image": ""  
    }  
}  
Listar Todos los Blogs  
URL: GET http://localhost:8080/api/v1/blog/lis-all-blog  
Descripción: Devuelve la lista de todos los blogs registrados en el sistema.  

Módulo: Comentario  
Crear Comentario  
URL: POST http://localhost:8080/api/v1/comment/create-comment/{idBlog}  
Parámetro en la URL: idBlog – ID del blog al que se asocia el comentario.  
Body de ejemplo:  
{  
  "name": "Vaneza Jhenny",  
  "paternalSurname": "Hilari",  
  "maternalSurname": "Huanca",  
  "email": "vane@gmail.com",  
  "country": "Bolivia",  
  "punctuation": 5  
}  
Listar Todos los Comentarios  
URL: GET http://localhost:8080/api/v1/comment/lis-all-comment  
Descripción: Retorna todos los comentarios registrados, junto con la información del blog al que pertenecen.  
