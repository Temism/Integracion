package cl.Ferramas.Ferramas.dto;

import java.time.LocalDateTime;

public class MovimientoInventarioDTO {

    private Long Id;
    private Long Idproducto;
    private Long idSucursal;
    private Long idtipomov;
    private Integer Cantidad;
    private LocalDateTime fechaMovimiento;
    private Long idUsuario;
    private Long idPedido;
    private String motivo;


    public MovimientoInventarioDTO(Long id, Long idproducto, Long idSucursal, Long idtipomov, Integer cantidad, LocalDateTime fechaMovimiento, Long idUsuario, Long idPedido, String motivo) {
        Id = id;
        Idproducto = idproducto;
        this.idSucursal = idSucursal;
        this.idtipomov = idtipomov;
        Cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.idUsuario = idUsuario;
        this.idPedido = idPedido;
        this.motivo = motivo;
    }

    public MovimientoInventarioDTO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getIdproducto() {
        return Idproducto;
    }

    public void setIdproducto(Long idproducto) {
        Idproducto = idproducto;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Long getIdtipomov() {
        return idtipomov;
    }

    public void setIdtipomov(Long idtipomov) {
        this.idtipomov = idtipomov;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
