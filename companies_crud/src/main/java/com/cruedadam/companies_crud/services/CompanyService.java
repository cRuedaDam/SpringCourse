package com.cruedadam.companies_crud.services;

import com.cruedadam.companies_crud.entities.Company;

// Interfaz que define el contrato del servicio de negocio para la entidad Company
public interface CompanyService {
    Company readByName(String name); // Obtener una compañía por su nombre
    Company create(Company company); // Crear una nueva compañía en la base de datos
    Company update(Company company, String name); // Actualizar una compañía existente identificada por nombre
    void delete(String name); // Eliminar una compañía por su nombre
}
