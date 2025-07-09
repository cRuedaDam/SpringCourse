package com.cruedadam.companies_crud.repositories;

import com.cruedadam.companies_crud.entities.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz representa el repositorio de acceso a datos para la entidad WebSite.
// Al extender JpaRepository, se heredan muchos métodos como save(), findById(), findAll(), deleteById(), etc.
public interface WebSiteRepo extends JpaRepository<WebSite, Long> {
}
