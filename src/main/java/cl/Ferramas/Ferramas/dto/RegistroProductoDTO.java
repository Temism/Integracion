package cl.Ferramas.Ferramas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroProductoDTO {


    @NotBlank
    @Size(min = 1, message = "El codigo no puede estar vacio")
    private String codigo;
    @NotBlank
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    private String descripcion;

    private Long marca;
    private Long categoria;
    private BigDecimal precioActual;
    private BigDecimal costo;
    private Integer garantiaMeses;
    private Boolean activo;

    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;


    public RegistroProductoDTO(String codigo, String nombre, String descripcion, Long marca, Long categoria, BigDecimal precioActual, BigDecimal costo, Integer garantiaMeses, Boolean activo, LocalDate fechaCreacion, LocalDate fechaActualizacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.precioActual = precioActual;
        this.costo = costo;
        this.garantiaMeses = garantiaMeses;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public RegistroProductoDTO() {
    }

    public @NotBlank @Size(min = 1, message = "El codigo no puede estar vacio") String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NotBlank @Size(min = 1, message = "El codigo no puede estar vacio") String codigo) {
        this.codigo = codigo;
    }

    public @NotBlank @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getMarca() {
        return marca;
    }

    public void setMarca(Long marca) {
        this.marca = marca;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(BigDecimal precioActual) {
        this.precioActual = precioActual;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(Integer garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
