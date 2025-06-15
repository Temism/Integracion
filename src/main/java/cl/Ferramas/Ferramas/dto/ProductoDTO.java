package cl.Ferramas.Ferramas.dto;



import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String marca;
    private String categoria;
    private BigDecimal precioActual;
    private BigDecimal costo;
    private Integer garantiaMeses;
    private Boolean activo;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private String imagenUrl;


    public ProductoDTO(Long id,String codigo, String nombre, String descripcion, String marca, String categoria, BigDecimal precioActual, BigDecimal costo, Integer garantiaMeses, Boolean activo, LocalDate fechaCreacion, LocalDate fechaActualizacion) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoDTO() {
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
