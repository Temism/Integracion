package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.services.ReporteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.io.IOException;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/ventas")
    public void generarReporteVentas(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_ventas.pdf");

        reporteService.generarInformeVentas(response);
    }
    
    @GetMapping("/pagos")
    public void generarInformePagos(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=informe_pagos.pdf");
        reporteService.generarInformePagos(response);
    }
    
    @GetMapping("/productos-mas-vendidos")
    public void generarInformeProductosMasVendidos(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=productos_mas_vendidos.pdf");
        reporteService.generarInformeProductosMasVendidos(response);
    }

    @GetMapping("/dashboard")
    public Map<String, Object> obtenerResumenDashboard() {
        return reporteService.obtenerResumenDashboard();
    }
}
