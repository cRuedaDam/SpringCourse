package com.cruedadam.companies_crud.controllers;

import com.cruedadam.companies_crud.entities.Company;
import com.cruedadam.companies_crud.services.CompanyService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation; // Para documentar cada endpoint individualmente
import io.swagger.v3.oas.annotations.tags.Tag; // Para agrupar los endpoints bajo una etiqueta común en Swagger

import lombok.AllArgsConstructor; // Crea automáticamente un constructor con todos los atributos final
import lombok.extern.slf4j.Slf4j; // Habilita el uso de logs con SLF4J

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController // Marca la clase como un controlador REST (retorna JSON en lugar de vistas)
@AllArgsConstructor // Inyección de dependencias por constructor, generada automáticamente por Lombok
@RequestMapping(path = "company") // Ruta base de este recurso: /company
@Slf4j // Habilita el logger 'log'
@Tag(name = "Companies Resource") // Nombre del grupo de endpoints en Swagger UI
public class CompanyController {

    // Inyección del servicio que contiene la lógica de negocio
    private final CompanyService companyService;

    // === [ GET ] ===
    @Operation(summary = "Get companies data by name") // Descripción breve en Swagger
    @GetMapping(path = "{name}") // Ruta: /company/{name}
    @Observed(name = "company.name")
    @Timed(value = "company.name")
    public ResponseEntity<Company> get(@PathVariable String name){
        log.info("GET: company {}", name);
        return ResponseEntity.ok(this.companyService.readByName(name)); // Llama al servicio para buscar la empresa por nombre
    }

    // === [ POST ] ===
    @Operation(summary = "Post in DB for a company based in its name")
    @PostMapping
    @Observed(name = "company.save")
    @Timed(value = "company.save")
    public ResponseEntity<Company> post(@RequestBody Company company){
        log.info("POST: company {}", company.getName());
        // Guarda la compañía y retorna 201 Created con la URI construida a partir del nombre
        return ResponseEntity.created(
                URI.create(this.companyService.create(company).getName())
        ).build();
    }

    // === [ PUT ] ===
    @Operation(summary = "Update in DB for a company based in its name")
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(@RequestBody Company company, @PathVariable String name){
        log.info("PUT: company {}", name);
        return ResponseEntity.ok(this.companyService.update(company, name));
    }

    // === [ DELETE ] ===
    @Operation(summary = "Delete a company from the DB by name")
    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(@PathVariable String name){
        log.info("DELETE: company {}", name);
        this.companyService.delete(name);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content si la eliminación fue exitosa
    }
}
