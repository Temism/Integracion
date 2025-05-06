package cl.Ferramas.Ferramas.dto;

public class InventarioDTO {

    private Long Id;
    private Integer stockActual;
    private Integer stockMinimo;
    private String Ubicacion;
    private Long ProductoId;
    private Long SucursalId;
    private String producto;


    public InventarioDTO(Long id,String producto ,Integer stockActual, Integer stockMinimo, String ubicacion, Long productoId, Long sucursalId) {
        this.Id = id;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        Ubicacion = ubicacion;
        ProductoId = productoId;
        SucursalId = sucursalId;
    }

    public InventarioDTO() {
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public Long getProductoId() {
        return ProductoId;
    }

    public void setProductoId(Long productoId) {
        ProductoId = productoId;
    }

    public Long getSucursalId() {
        return SucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        SucursalId = sucursalId;
    }
}


