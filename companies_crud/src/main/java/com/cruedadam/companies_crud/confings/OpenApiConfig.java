package com.cruedadam.companies_crud.confings;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/*
 * Esta clase está marcada como una configuración de Spring.
 * Spring la reconocerá como una clase que define beans o configuraciones del contexto de la aplicación.
 */
@Configuration
@OpenAPIDefinition( // Anotación que permite definir información básica de la documentación OpenAPI
        info = @Info(
                title = "companies CRUD", // Título que aparecerá en la documentación
                version = "1.0.0", // Versión de la API
                description = "This is a CRUD for management companies" // Descripción general del servicio
        )
)
public class OpenApiConfig {
}
