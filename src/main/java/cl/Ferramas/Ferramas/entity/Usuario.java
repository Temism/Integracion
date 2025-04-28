package cl.Ferramas.Ferramas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    @Column( nullable = false)
    private String email;
    @Column( nullable = false)
    private String apellidom ;
    @Column(nullable = false)
    private String password;
    @Column( nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidop;
    @Column(nullable = false)
    private String rut;
    private String telefono;
    @Column(nullable = false)
    private Date fechaRegistro;
    @Column( nullable = false)
    private Date ultimoLogin;
    private Boolean activo = true;
    private String Direccion;
    private Date fechaNacimiento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "vendedor")

    private List<Pedido> pedidosVendidos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")

    private List<HistorialPrecio> historialPrecios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")

    private List<MovimientoInventario> movimientosInventario = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")

    private List<HistorialEstadoPedido> historialEstadosPedido = new ArrayList<>();

    public Usuario(Long usuarioId, String email, String apellidom, String password, String nombre, String apellidop, String rut, String telefono, Date fechaRegistro, Date ultimoLogin, Boolean activo, String direccion, Date fechaNacimiento, Comuna comuna, Rol rol, Sucursal sucursal, List<Pedido> pedidos, List<Pedido> pedidosVendidos, List<HistorialPrecio> historialPrecios, List<MovimientoInventario> movimientosInventario, List<HistorialEstadoPedido> historialEstadosPedido) {
        this.usuarioId = usuarioId;
        this.email = email;
        this.apellidom = apellidom;
        this.password = password;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.rut = rut;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.ultimoLogin = ultimoLogin;
        this.activo = activo;
        Direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.comuna = comuna;
        this.rol = rol;
        this.sucursal = sucursal;
        this.pedidos = pedidos;
        this.pedidosVendidos = pedidosVendidos;
        this.historialPrecios = historialPrecios;
        this.movimientosInventario = movimientosInventario;
        this.historialEstadosPedido = historialEstadosPedido;
    }

    public Usuario() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidosVendidos() {
        return pedidosVendidos;
    }

    public void setPedidosVendidos(List<Pedido> pedidosVendidos) {
        this.pedidosVendidos = pedidosVendidos;
    }

    public List<HistorialPrecio> getHistorialPrecios() {
        return historialPrecios;
    }

    public void setHistorialPrecios(List<HistorialPrecio> historialPrecios) {
        this.historialPrecios = historialPrecios;
    }

    public List<MovimientoInventario> getMovimientosInventario() {
        return movimientosInventario;
    }

    public void setMovimientosInventario(List<MovimientoInventario> movimientosInventario) {
        this.movimientosInventario = movimientosInventario;
    }

    public List<HistorialEstadoPedido> getHistorialEstadosPedido() {
        return historialEstadosPedido;
    }

    public void setHistorialEstadosPedido(List<HistorialEstadoPedido> historialEstadosPedido) {
        this.historialEstadosPedido = historialEstadosPedido;
    }

    // MÉTODOS HELPER

    /**
     * Obtiene el nombre completo del usuario
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidop + " " +  this.apellidom;
    }

    /**
     * Actualiza la fecha del último login
     */
    public void registrarLogin() {
        this.ultimoLogin = new Date();
    }




}
