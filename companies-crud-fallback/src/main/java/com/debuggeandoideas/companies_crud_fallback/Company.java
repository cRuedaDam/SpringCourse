package com.debuggeandoideas.companies_crud_fallback;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data // Anotación de Lombok que genera getters, setters, equals, hashCode y toString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long id;
    private String name;     // Nombre de la empresa
    private String founder;  // Fundador de la empresa
    private String logo;     // Ruta o URL del logo
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") // Formato de serialización JSON para fechas
    private LocalDate foundationDate; // Fecha de fundación
    private List<WebSite> webSites; // Lista de sitios web asociados a la empresa
}
