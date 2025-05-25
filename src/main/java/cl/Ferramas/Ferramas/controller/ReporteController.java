package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.services.ReporteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
