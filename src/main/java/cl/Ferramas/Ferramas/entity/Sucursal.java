package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long sucursalId;
    @Column( nullable = false)
    private String nombre;
    @Column( nullable = false)
    private String calle;
    @Column( nullable = false)
    private String numero;

    @Column(name = "telefono_sucursal")
    private String telefono;
    @Column(name = "email_sucursal")
    private String email;
    private String horario;
    private Boolean esBodegaCentral;


    @ManyToOne
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;


    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    private List<UbicacionBodega> ubicaciones = new ArrayList<>();

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    private List<Inventario> inventarios = new ArrayList<>();

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientosOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "sucursalDestino",fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientosDestino = new ArrayList<>();


    public Sucursal(Long id, String nombre, String calle, String numero, String telefono, String email, String horario, Boolean esBodegaCentral, Comuna comuna, Ciudad ciudad, Region region, List<UbicacionBodega> ubicaciones, List<Inventario> inventarios, List<Pedido> pedidos, List<MovimientoInventario> movimientosOrigen, List<MovimientoInventario> movimientosDestino) {
        this.sucursalId = id;
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.telefono = telefono;
        this.email = email;
        this.horario = horario;
        this.esBodegaCentral = esBodegaCentral;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.region = region;
        this.ubicaciones = ubicaciones;
        this.inventarios = inventarios;
        this.pedidos = pedidos;
        this.movimientosOrigen = movimientosOrigen;
        this.movimientosDestino = movimientosDestino;
    }

    public Sucursal() {
    }


    public Boolean getEsBodegaCentral() {
        return esBodegaCentral;
    }

    public void setEsBodegaCentral(Boolean esBodegaCentral) {
        this.esBodegaCentral = esBodegaCentral;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<UbicacionBodega> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<UbicacionBodega> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<MovimientoInventario> getMovimientosOrigen() {
        return movimientosOrigen;
    }

    public void setMovimientosOrigen(List<MovimientoInventario> movimientosOrigen) {
        this.movimientosOrigen = movimientosOrigen;
    }

    public List<MovimientoInventario> getMovimientosDestino() {
        return movimientosDestino;
    }

    public void setMovimientosDestino(List<MovimientoInventario> movimientosDestino) {
        this.movimientosDestino = movimientosDestino;
    }


    // MÉTODOS HELPER

    /**
     * Obtiene la dirección completa de la sucursal
     */
    public String getDireccionCompleta() {
        return this.calle + " " + this.numero + ", " +
                this.comuna.getNombre() + ", " +
                this.ciudad.getNombre() + ", " +
                this.region.getNombre();
    }

    /**
     * Verifica si la sucursal es una bodega central
     */
    public boolean esBodegaCentral() {
        return this.esBodegaCentral != null && this.esBodegaCentral;
    }


}
