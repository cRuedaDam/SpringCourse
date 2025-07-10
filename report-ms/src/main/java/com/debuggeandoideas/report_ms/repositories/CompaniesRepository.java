package com.debuggeandoideas.report_ms.repositories;

import com.debuggeandoideas.report_ms.beans.LoadBalancerConfiguration;
import com.debuggeandoideas.report_ms.models.Company;
import jakarta.ws.rs.Path;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 - Es un cliente Feign que se comunica con el microservicio llamado companies-crud.
 - Gracias a @FeignClient, Spring genera la implementación en tiempo de ejecución.
 - @LoadBalancerClient le dice que para este cliente use la configuración definida en LoadBalancerConfiguration.
 */

@FeignClient(name = "companies-crud")
@LoadBalancerClient(name = "companies-crud", configuration = LoadBalancerConfiguration.class)
public interface CompaniesRepository {

    @GetMapping(path = "/companies-crud/company/{name}")
    Optional<Company> getByName(@PathVariable String name);

    @PostMapping(path = "/companies-crud/company")
    Optional<Company> postByName(@RequestBody Company company);

    @DeleteMapping(path = "/companies-crud/company/{name}")
    void deleteByName(@PathVariable String name);
}
