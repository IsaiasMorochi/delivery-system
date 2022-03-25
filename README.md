# API REST Delivery System

Aplicación REST en Java con el framewordk `Spring Boot` con el objetivo de administrar la asiganacion de productos a pedidos, tomando como estrategia de implementacion una Arquitectura Hexagonal + DDD y buenas pracitas como HATEOS, Spring Data Specification, 

### Domain-Driven Design
DDD es un paradigma de diseño de dominio que propone una arquitectura de software basada en la arquitectura de dominio, donde las las clases, metodos, variables deben reflejar el dominio de negocio.

#### Patrones estrategicos
* Lengua ubicuo
* Subdominio
* Bounded Context

#### Patrones tacticos
* Entidad o Entity
* Value Object
* Agregado o aggregate
* Servicio o Service
* Repositorio o Repository
* Eventos de dominio o domain events

La aplicacion presenta la siguiente estructura:

```
└── src
    └── main
       └── java
           └── delivery
               └── pedido
                │   │   └── querybus
                │   │   └── use-cases
                │   │   └── services
                │   │── domain
                │   │   └── model
                │   │   └── exceptions
                │   └── infrastructure
                │        └── repository
                │        └── rest
                │            └── controllers
                └── producto
                    └── application
                    └── domain
                    └── infrastructure           
                       
```                 

## API REST con base en [Maturity Model Richarzon](https://martinfowler.com/articles/richardsonMaturityModel.html)
El Richardson Maturity Model califica un API en función de su adherencia a las restricciones de la arquitectura REST. Mientras más ajustada sea tu implementación, más arriba en la escala estará nuestra API. 

Se la puede distinguir en los siguientes niveles.

* *Nivel 0*: The Swamp of POX (Plain Old XML)
Usa el protocolo HTTP como transporte pero no para indicar el estado de la aplicación, semejante al RPC clásico: SOAP y XML-RPC.

* *Nivel 1*: Resources
Distingue entre diferentes recursos, pero usa un solo método HTTP.

* *Nivel 2*: HTTP Verbs
Completo uso de los verbos HTTP combinado con sustantivos de los recursos.

* *Nivel 3*: HYPERMEDIA
Usa HATEOAS (Hypermedia As The Engine Of Application State) para dirigir el estado de la aplicación.

## Patron Specification Criteria

Para realizar busquedas por cualquiera de sus campos en la entidad producto se realizo la implementacion de Spring Data JPA Specification, Patron Criteria.

La implementación de SearchCriteria contiene una representación básica de una restricción, y se basa en esta restricción en la que construiremos la consulta:

**Clave**: el nombre del campo, por ejemplo, productName, etc.

**Operación**: la operación, por ejemplo, igualdad, menor que, etc.

**Value**: el valor del campo, por ejemplo, Sony, 25, etc.

> De igual forma se implemeno consultas y registros personalizadas con Spring Data JPA

```java
    @Modifying
    @Query(value = "INSERT INTO pedido_producto (id_pedido, id_producto) values (:idPedido, :idProducto)", nativeQuery = true)
    Integer savePedidoProducto(@Param("idPedido") Long idPedido, @Param("idProducto") Long idProducto);
```


### Puntos pendientes de implentación y mejora.
* Eventos de dominio inmutables
* Agregados que no puedan acceder a repositorios
* Implentación de Event Sourcing y CQRS


### Entregables

* [Repositorio](https://github.com/IsaiasMorochi/delivery-system.git/)
* [Reseña](https://github.com/IsaiasMorochi/delivery-system.git/))
* [Documentacion de API](doc/endpoints/delivery-system-openapi.json)

![OpenApi]git s(doc/images/openapi.png)


## Documentacion Api Rest
Se realizo la implemntacion de Open API para autodocumentar la API, mismo que se encuentra 
detallado en **[Documentacion OpenApi](doc/endpoints/delivery-system-openapi.json)**.

### Tecnologias

* [Java +11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [Sprint Boot](https://spring.io/projects/spring-boot)
* [Maven](https://docs.spring.io/spring-boot/docs/2.6.5/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.6.5/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Open Api](https://swagger.io/specification/)

### Referencias

* [Arquitectura Hexagonal y DDD](https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
 