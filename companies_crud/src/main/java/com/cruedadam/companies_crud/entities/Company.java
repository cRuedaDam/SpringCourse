package com.cruedadam.companies_crud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity // Marca la clase como una entidad JPA para persistencia en base de datos
@Data // Anotación de Lombok que genera getters, setters, equals, hashCode y toString
public class Company {

    @Id // Clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;
    private String name;     // Nombre de la empresa
    private String founder;  // Fundador de la empresa
    private String logo;     // Ruta o URL del logo
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") // Formato de serialización JSON para fechas
    private LocalDate foundationDate; // Fecha de fundación
    @OneToMany(
            fetch = FetchType.LAZY, // Carga diferida: se accede a los sitios solo cuando se usan
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE} // Propagación de operaciones
    )
    @JoinColumn(name = "id_company", referencedColumnName = "id") // Define cómo se une con la tabla WebSite
    private List<WebSite> webSites; // Lista de sitios web asociados a la empresa
}
