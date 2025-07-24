package com.cruedadam.companies_crud_fallback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data // Lombok genera getters, setters, toString, equals y hashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSite implements Serializable {

    private String name; // Nombre del sitio web (por ejemplo: "YouTube", "GitHub")
}
