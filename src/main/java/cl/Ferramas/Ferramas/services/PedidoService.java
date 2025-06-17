package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.dto.CambioEstadoDTO;
import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.mapper.PedidoMapper;
import cl.Ferramas.Ferramas.repository.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRep pedidoRep;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private UsuarioRep usuarioRep;
    @Autowired
    private EstadoPedidoRep estadoPedidoRep;
    @Autowired
    private TipoEntregaRep tipoEntregaRep;
    @Autowired
    private SucursalRep sucursalRep;
    @Autowired
    private HistorialEstadoPedidoRep historialEstadoPedidoRep;
    @Autowired
    private MetodoPagoRep metodoPagoRep;

    @Transactional
    public List<PedidoDTO> listarpedidos() {
        List<Pedido> pedidos = pedidoRep.findAll();
        return pedidos.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO buscarpedidoporId(Long id){
        Optional<Pedido> pedidoOptional = pedidoRep.findById(id);
        return pedidoOptional.map(pedidoMapper::toDTO).orElse(null);
    }

    public byte[] generarPdfPedido(Long pedidoId) throws Exception {
        // Verificar que el pedido existe
        Pedido pedido = pedidoRep.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido con ID " + pedidoId + " no encontrado"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Configuración de fuentes
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Contenido del PDF
            document.add(new Paragraph("FERRAMAS - Detalle del Pedido", headerFont));
            document.add(new Paragraph(" ")); // Espacio

            document.add(new Paragraph("Código: " + (pedido.getCodigoPedido() != null ? pedido.getCodigoPedido() : "N/A"), normalFont));

            // Manejo seguro de fecha
            String fechaFormateada = "N/A";
            if (pedido.getFechaPedido() != null) {
                fechaFormateada = pedido.getFechaPedido().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
            document.add(new Paragraph("Fecha: " + fechaFormateada, normalFont));

            document.add(new Paragraph("Cliente: " + (pedido.getCliente() != null ? pedido.getCliente().getNombre() : "N/A"), normalFont));
            document.add(new Paragraph("Vendedor: " + (pedido.getVendedor() != null ? pedido.getVendedor().getNombre() : "N/A"), normalFont));
            document.add(new Paragraph("Dirección de entrega: " + (pedido.getDireccionEntrega() != null ? pedido.getDireccionEntrega() : "N/A"), normalFont));
            document.add(new Paragraph("Estado: " + (pedido.getEstado() != null ? pedido.getEstado().getNombre() : "N/A"), normalFont));
            document.add(new Paragraph("Tipo Entrega: " + (pedido.getTipoEntrega() != null ? pedido.getTipoEntrega().getNombre() : "N/A"), normalFont));
            document.add(new Paragraph("Sucursal: " + (pedido.getSucursal() != null ? pedido.getSucursal().getNombre() : "N/A"), normalFont));
            document.add(new Paragraph("Método de Pago: " + (pedido.getMetodoPago() != null ? pedido.getMetodoPago().getNombre() : "N/A"), normalFont));

            document.add(new Paragraph(" ")); // Espacio

            // Información financiera
            document.add(new Paragraph("--- Resumen  ---", headerFont));
            document.add(new Paragraph("Subtotal: $" + (pedido.getSubtotal() != null ? pedido.getSubtotal() : "0"), normalFont));
            document.add(new Paragraph("Descuento: $" + (pedido.getDescuento() != null ? pedido.getDescuento() : "0"), normalFont));
            document.add(new Paragraph("IVA: $" + (pedido.getIva() != null ? pedido.getIva() : "0"), normalFont));
            document.add(new Paragraph("Total: $" + (pedido.getTotal() != null ? pedido.getTotal() : "0"), normalFont));

            document.add(new Paragraph(" ")); // Espacio
            document.add(new Paragraph("Notas: " + (pedido.getNotas() != null && !pedido.getNotas().trim().isEmpty() ? pedido.getNotas() : "Sin notas adicionales"), normalFont));

        } catch (DocumentException e) {
            throw new RuntimeException("Error creando el documento PDF", e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }

        return baos.toByteArray();
    }


    @Transactional
    public PedidoDTO registrarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

        Usuario cliente = usuarioRep.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        pedido.setCliente(cliente);

        Usuario vendedor = usuarioRep.findById(pedidoDTO.getVendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        pedido.setVendedor(vendedor);

        EstadoPedido estado = estadoPedidoRep.findById(pedidoDTO.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        pedido.setEstado(estado);

        TipoEntrega tipoEntrega = tipoEntregaRep.findById(pedidoDTO.getTipoEntregaId())
                .orElseThrow(() -> new RuntimeException("Tipo de entrega no encontrado"));
        pedido.setTipoEntrega(tipoEntrega);

        Sucursal sucursal = sucursalRep.findById(pedidoDTO.getSucursalId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        pedido.setSucursal(sucursal);

        MetodoPago metodoPago = metodoPagoRep.findById(pedidoDTO.getMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        pedido.setMetodoPago(metodoPago);

        pedido = pedidoRep.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    @Transactional
    public void actualizarComprobante(Long pedidoId, String url) {
        Pedido pedido = pedidoRep.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setComprobanteUrl(url);
        pedidoRep.save(pedido);
    }

    @Transactional
    public boolean cambiarEstadoPedido(Long pedidoId, CambioEstadoDTO dto) {
        Optional<Pedido> optionalPedido = pedidoRep.findById(pedidoId);
        if (optionalPedido.isEmpty()) return false;

        Pedido pedido = optionalPedido.get();
        EstadoPedido estadoAnterior = pedido.getEstado();

        EstadoPedido nuevoEstado = estadoPedidoRep.findById(dto.getEstadoNuevoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        Usuario usuario = usuarioRep.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        pedido.setEstado(nuevoEstado);
        pedidoRep.save(pedido);

        HistorialEstadoPedido historial = new HistorialEstadoPedido();
        historial.setPedido(pedido);
        historial.setEstadoAnterior(estadoAnterior);
        historial.setEstadoNuevo(nuevoEstado);
        historial.setUsuario(usuario);
        historial.setFechaCambio(LocalDateTime.now());
        historial.setComentario(dto.getComentario());

        historialEstadoPedidoRep.save(historial);

        return true;
    }
}
