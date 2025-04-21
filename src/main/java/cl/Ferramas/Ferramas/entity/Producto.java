package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;


import java.util.*;


@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    @Column( nullable = false, unique = true)
    private String codigo;
    @Column( nullable = false)
    private String codigoFabricante;
    @Column( nullable = false, unique = true)
    private String nombre;
    @Column( nullable = false)
    private String descripcion;
    @Column( nullable = false)
    private String imagenUrl;
    @Column( nullable = false)
    private Double peso;
    @Column( nullable = false)
    private String dimensiones;
    @Column(nullable = false)
    private String unidadMedida;
    private Boolean activo;
    @Column( nullable = false)
    private Date fechaCreacion;


    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<Precio> precios = new ArrayList<>();

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<Inventario> inventarios = new ArrayList<>();

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetallePedido> detallesPedido = new ArrayList<>();

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientos = new ArrayList<>();

    public Producto(Long idProducto, String codigo, String codigoFabricante, String nombre, String descripcion, String imagenUrl, Double peso, String dimensiones, String unidadMedida, Boolean activo, Date fechaCreacion, Marca marca, Set<Categoria> categorias, List<Precio> precios, List<Inventario> inventarios, List<DetallePedido> detallesPedido, List<MovimientoInventario> movimientos) {
        this.productoId = idProducto;
        this.codigo = codigo;
        this.codigoFabricante = codigoFabricante;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.unidadMedida = unidadMedida;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.marca = marca;
        this.categorias = categorias;
        this.precios = precios;
        this.inventarios = inventarios;
        this.detallesPedido = detallesPedido;
        this.movimientos = movimientos;
    }

    public Producto() {
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(String codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Precio> getPrecios() {
        return precios;
    }

    public void setPrecios(List<Precio> precios) {
        this.precios = precios;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public List<MovimientoInventario> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoInventario> movimientos) {
        this.movimientos = movimientos;
    }

    // MÉTODOS HELPER

    /**
     * Obtiene el precio actual (vigente) del producto
     */
    public Precio getPrecioActual() {
        Date ahora = new Date();
        Precio precioActual = null;
        Date fechaMasReciente = null;

        for (Precio precio : this.precios) {
            // Verificar si el precio está vigente (inicio <= ahora < fin)
            if (precio.getFechainicio().before(ahora) &&
                    (precio.getFechafin() == null || precio.getFechafin().after(ahora))) {
                // Si no hay precio o este precio es más reciente que el anterior
                if (fechaMasReciente == null || precio.getFechainicio().after(fechaMasReciente)) {
                    precioActual = precio;
                    fechaMasReciente = precio.getFechainicio();
                }
            }
        }

        return precioActual;
    }

    /**
     * Obtiene el valor del precio actual
     */
    public Double getPrecioActualValor() {
        Precio precio = getPrecioActual();
        return precio != null ? precio.getValor() : 0.0;
    }

    /**
     * Registra un nuevo precio para el producto
     */
    public Precio registrarNuevoPrecio(Double valor, String moneda) {
        // Finalizar el precio actual si existe
        Precio precioActual = getPrecioActual();
        if (precioActual != null) {
            precioActual.setFechafin(new Date());
        }

        // Crear nuevo precio
        Precio nuevoPrecio = new Precio();
        nuevoPrecio.setProducto(this);
        nuevoPrecio.setValor(valor);
        nuevoPrecio.setMoneda(moneda);
        nuevoPrecio.setFechainicio(new Date());

        this.precios.add(nuevoPrecio);
        return nuevoPrecio;
    }

    /**
     * Agrega una categoría al producto
     */
    public void agregarCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }

    /**
     * Remueve una categoría del producto
     */
    public void removerCategoria(Categoria categoria) {
        this.categorias.remove(categoria);
    }

    /**
     * Verifica si el producto pertenece a una categoría
     */
    public boolean perteneceACategoria(Long categoriaId) {
        for (Categoria categoria : this.categorias) {
            if (categoria.getId().equals(categoriaId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el producto está activo
     */
    public boolean estaActivo() {
        return this.activo != null && this.activo;
    }

    /**
     * Verifica si el producto tiene stock en alguna sucursal
     */
    public boolean tieneStockEnAlgunaSucursal(List<Inventario> inventarios) {
        for (Inventario inventario : inventarios) {
            if (inventario.getProducto().getProductoId().equals(this.productoId) &&
                    inventario.getStockActual() > 0) {
                return true;
            }
        }
        return false;
    }

}

