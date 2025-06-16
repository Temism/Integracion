package cl.Ferramas.Ferramas.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoDTO {

    private Long id;
    private Long idPedido;
    private Long idMetodopago;
    private BigDecimal monto;
    private LocalDateTime fechapago;
    private Long idEstado;
    private String codigoTransaccion;
    private String datosTransaccion;

    public PagoDTO(Long id, Long idPedido, Long idMetodopago, BigDecimal monto, LocalDateTime fechapago, Long idEstado, String codigoTransaccion, String datosTransaccion) {
        this.id = id;
        this.idPedido = idPedido;
        this.idMetodopago = idMetodopago;
        this.monto = monto;
        this.fechapago = fechapago;
        this.idEstado = idEstado;
        this.codigoTransaccion = codigoTransaccion;
        this.datosTransaccion = datosTransaccion;
    }

    public PagoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdMetodopago() {
        return idMetodopago;
    }

    public void setIdMetodopago(Long idMetodopago) {
        this.idMetodopago = idMetodopago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechapago() {
        return fechapago;
    }

    public void setFechapago(LocalDateTime fechapago) {
        this.fechapago = fechapago;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public String getDatosTransaccion() {
        return datosTransaccion;
    }

    public void setDatosTransaccion(String datosTransaccion) {
        this.datosTransaccion = datosTransaccion;
    }
}
