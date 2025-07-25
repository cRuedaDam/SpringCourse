package com.debuggeandoideas.report_ms.controllers;

import com.debuggeandoideas.report_ms.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 - Expone un endpoint HTTP: GET /report/{name}.
 - Llama a makeReport() y devuelve un JSON como: { "report": "NombreEmpresa" }
 */

@RestController
@RequestMapping(path = "report")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;


    @GetMapping(path = "{name}")
    public ResponseEntity<Map<String, String>> getReport(@PathVariable String name){
        var response = Map.of("report", this.reportService.makeReport(name));
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<String> postReport(@RequestBody String report){
        return ResponseEntity.ok(this.reportService.saveReport(report));
    }

    @DeleteMapping(path = "{name}")
    public ResponseEntity<Void> deleteReport(@PathVariable String name){
        this.reportService.deleteReport(name);
        return ResponseEntity.noContent().build();
    }
}
