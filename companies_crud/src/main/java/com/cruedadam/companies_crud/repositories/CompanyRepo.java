package com.cruedadam.companies_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cruedadam.companies_crud.entities.Company;

import java.util.Optional;

// Esta interfaz representa el repositorio de acceso a datos para la entidad Company.
// Al extender JpaRepository, se heredan muchos métodos como save(), findById(), findAll(), deleteById(), etc.

public interface CompanyRepo extends JpaRepository<Company, Long> {

    // Métod personalizado: busca una compañía por su nombre
    // Spring Data JPA lo implementa automáticamente por convención
    Optional<Company> findByName(String name);
}
