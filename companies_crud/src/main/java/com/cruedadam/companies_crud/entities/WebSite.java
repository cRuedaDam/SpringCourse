package com.cruedadam.companies_crud.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity // Marca esta clase como una entidad persistente (tabla en la base de datos)
@Data // Lombok genera getters, setters, toString, equals y hashCode
public class WebSite implements Serializable {
    @Id // Identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental (ideal para bases como MySQL/PostgreSQL)
    private Long id;
    private String name; // Nombre del sitio web (por ejemplo: "YouTube", "GitHub")
    @Column(columnDefinition = "category")
    @Enumerated(value = EnumType.STRING) // Guarda el enum como texto legible (e.g., "STREAMING", "SERVICES")
    private Category category; // Categoría del sitio (ligada al enum Category)
    private String description; // Descripción breve del sitio
}
