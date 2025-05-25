package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Pago;
import cl.Ferramas.Ferramas.entity.Pedido;
import cl.Ferramas.Ferramas.entity.DetallePedido;
import cl.Ferramas.Ferramas.repository.DetallePedidoRep;
import cl.Ferramas.Ferramas.repository.PagoRep;
import cl.Ferramas.Ferramas.repository.PedidoRep;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private PedidoRep pedidoRep;

    @Autowired
    private PagoRep pagoRep;

    @Autowired
    private DetallePedidoRep detallePedidoRep;

    public void generarInformeVentas(HttpServletResponse response) {
        try {
            List<Pedido> pedidos = pedidoRep.findAll();

            Document documento = new Document();
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(documento, out);

            documento.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Informe de Ventas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); // espacio

            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{2f, 2f, 2f, 2f, 2f});

            tabla.addCell(celdaEncabezado("N° Pedido"));
            tabla.addCell(celdaEncabezado("Cliente"));
            tabla.addCell(celdaEncabezado("Fecha"));
            tabla.addCell(celdaEncabezado("Estado"));
            tabla.addCell(celdaEncabezado("Comprobante"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Pedido pedido : pedidos) {
                tabla.addCell(String.valueOf(pedido.getPedidoId()));
                tabla.addCell(pedido.getCliente().getNombre());
                tabla.addCell(pedido.getFechaPedido() != null ? pedido.getFechaPedido().format(formatter) : "N/A");
                tabla.addCell(pedido.getEstado().getNombre());
                tabla.addCell(pedido.getComprobanteUrl() != null ? pedido.getComprobanteUrl() : "No enviado");
            }

            documento.add(tabla);
            documento.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el informe de ventas: " + e.getMessage(), e);
        }
    }

    public void generarInformePagos(HttpServletResponse response) {
        try {
            List<Pago> pagos = pagoRep.findAll();

            Document documento = new Document();
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(documento, out);

            documento.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Informe de Pagos", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{1.5f, 2f, 2f, 2f, 2f, 3f});

            tabla.addCell(celdaEncabezado("ID Pago"));
            tabla.addCell(celdaEncabezado("Pedido"));
            tabla.addCell(celdaEncabezado("Cliente"));
            tabla.addCell(celdaEncabezado("Monto"));
            tabla.addCell(celdaEncabezado("Estado"));
            tabla.addCell(celdaEncabezado("Detalles"));

            for (Pago pago : pagos) {
                tabla.addCell(String.valueOf(pago.getPagoId()));
                tabla.addCell("Pedido #" + pago.getPedido().getPedidoId());
                tabla.addCell(pago.getPedido().getCliente().getNombre());
                tabla.addCell(pago.getMonto() != null ? "$" + pago.getMonto().toString() : "N/A");
                tabla.addCell(pago.getEstado().getNombre());
                tabla.addCell(pago.getDatosTransaccion() != null ? pago.getDatosTransaccion() : "Sin información");
            }

            documento.add(tabla);
            documento.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el informe de pagos: " + e.getMessage(), e);
        }
    }

    public void generarInformeProductosMasVendidos(HttpServletResponse response) {
        try {
            List<DetallePedido> detalles = detallePedidoRep.findAll();

            Map<String, Integer> resumen = new HashMap<>();
            for (DetallePedido detalle : detalles) {
                String nombre = detalle.getProducto().getNombre();
                int cantidad = detalle.getCantidad() != null ? detalle.getCantidad() : 0;
                resumen.put(nombre, resumen.getOrDefault(nombre, 0) + cantidad);
            }

            List<Map.Entry<String, Integer>> top = resumen.entrySet().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .collect(Collectors.toList());

            Document documento = new Document();
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(documento, out);

            documento.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Productos más vendidos", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{3f, 1f});

            tabla.addCell(celdaEncabezado("Producto"));
            tabla.addCell(celdaEncabezado("Cantidad Vendida"));

            for (Map.Entry<String, Integer> entry : top) {
                tabla.addCell(entry.getKey());
                tabla.addCell(String.valueOf(entry.getValue()));
            }

            documento.add(tabla);
            documento.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el informe de productos más vendidos: " + e.getMessage(), e);
        }
    }

    private PdfPCell celdaEncabezado(String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return celda;
    }
}
