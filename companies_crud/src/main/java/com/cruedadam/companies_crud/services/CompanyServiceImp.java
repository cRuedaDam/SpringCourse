package com.cruedadam.companies_crud.services;

import com.cruedadam.companies_crud.entities.Category;
import com.cruedadam.companies_crud.entities.Company;
import com.cruedadam.companies_crud.entities.Company;
import com.cruedadam.companies_crud.repositories.CompanyRepo;

import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service // Marca la clase como un servicio de Spring gestionado por el contenedor
@Transactional // Gestiona automáticamente las transacciones para los métodos públicos
@Slf4j // Habilita logger para agregar logs
@AllArgsConstructor // Lombok genera constructor con todos los atributos finales (inyección de dependencias)
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepo companyRepo; // Inyección del repositorio para acceso a datos
    private final Tracer tracer;


    @Override
    public Company readByName(String name) {
        var spam = tracer.nextSpan().name("readByName");

        try (Tracer.SpanInScope spanInScope = this.tracer.withSpan(spam.start())){
            log.info("Getting company from DB");
        }finally {
            spam.end();
        }

        // Busca la compañía por nombre, lanza excepción si no existe
        return this.companyRepo.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company create(Company company) {
        // Por cada sitio web asociado, si no tiene categoría, asigna NONE por defecto
        company.getWebSites().forEach(webSite -> {
            if (Objects.isNull(webSite.getCategory())) {
                webSite.setCategory(Category.NONE);
            }
        });
        // Guarda la compañía junto con los sitios web (por cascada)
        return this.companyRepo.save(company);
    }

    @Override
    public Company update(Company company, String name) {
        // Busca la compañía a actualizar, lanza excepción si no la encuentra
        var companyToUpdate = this.companyRepo.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        // Actualiza campos relevantes (no reemplaza la lista webSites ni nombre)
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        companyToUpdate.setFounder(company.getFounder());

        // Guarda los cambios en la base de datos
        return this.companyRepo.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        // Busca la compañía a eliminar, lanza excepción si no existe
        var companyToDelete = this.companyRepo.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        // Elimina la compañía de la base de datos
        this.companyRepo.delete(companyToDelete);
    }
}
