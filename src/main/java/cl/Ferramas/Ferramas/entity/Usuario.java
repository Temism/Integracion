package cl.Ferramas.Ferramas.entity;

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
    @Column(nullable = false)
    private String password;
    @Column( nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String rut;
    private String telefono;
    @Column(nullable = false)
    private Date fechaRegistro;
    @Column( nullable = false)
    private Date ultimoLogin;
    @Column(nullable = false)
    private Boolean cambioPassword = false;
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private List<Direccion> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private List<Pedido> pedidosCliente = new ArrayList<>();

    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY)
    private List<Pedido> pedidosVendedor = new ArrayList<>();

    @OneToMany(mappedBy = "bodeguero",fetch = FetchType.LAZY)
    private List<Pedido> pedidosBodeguero = new ArrayList<>();

    @OneToMany(mappedBy = "contador",fetch = FetchType.LAZY)
    private List<Pedido> pedidosContador = new ArrayList<>();


    public Usuario(Long id, String email, String password, String nombre, String apellido, String rut, String telefono, Date fechaRegistro, Date ultimoLogin, Boolean cambioPassword, Boolean activo, Rol rol, List<Direccion> direcciones, List<Pedido> pedidosCliente, List<Pedido> pedidosVendedor, List<Pedido> pedidosBodeguero, List<Pedido> pedidosContador) {
        this.usuarioId = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.ultimoLogin = ultimoLogin;
        this.cambioPassword = cambioPassword;
        this.activo = activo;
        this.rol = rol;
        this.direcciones = direcciones;
        this.pedidosCliente = pedidosCliente;
        this.pedidosVendedor = pedidosVendedor;
        this.pedidosBodeguero = pedidosBodeguero;
        this.pedidosContador = pedidosContador;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Boolean getCambioPassword() {
        return cambioPassword;
    }

    public void setCambioPassword(Boolean cambioPassword) {
        this.cambioPassword = cambioPassword;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidosCliente() {
        return pedidosCliente;
    }

    public void setPedidosCliente(List<Pedido> pedidosCliente) {
        this.pedidosCliente = pedidosCliente;
    }

    public List<Pedido> getPedidosVendedor() {
        return pedidosVendedor;
    }

    public void setPedidosVendedor(List<Pedido> pedidosVendedor) {
        this.pedidosVendedor = pedidosVendedor;
    }

    public List<Pedido> getPedidosBodeguero() {
        return pedidosBodeguero;
    }

    public void setPedidosBodeguero(List<Pedido> pedidosBodeguero) {
        this.pedidosBodeguero = pedidosBodeguero;
    }

    public List<Pedido> getPedidosContador() {
        return pedidosContador;
    }

    public void setPedidosContador(List<Pedido> pedidosContador) {
        this.pedidosContador = pedidosContador;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    // MÉTODOS HELPER

    /**
     * Obtiene el nombre completo del usuario
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Verifica si el usuario está activo
     */
    public boolean estaActivo() {
        return this.activo != null && this.activo;
    }

    /**
     * Verifica si el usuario tiene un rol específico
     */
    public boolean tieneRol(String nombreRol) {
        return this.rol != null && nombreRol.equals(this.rol.getNombre());
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la almacenada
     * (utiliza BCrypt para encripción)
     */
    public boolean verificarPassword(String passwordIngresada) {
        // En una implementación real usar BCryptPasswordEncoder
        return new BCryptPasswordEncoder().matches(passwordIngresada, this.password);
    }

    /**
     * Cambia la contraseña del usuario
     */
    public void cambiarPassword(String nuevaPassword) {
        // En una implementación real usar BCryptPasswordEncoder
        this.password = new BCryptPasswordEncoder().encode(nuevaPassword);
        this.cambioPassword = true;
    }

    /**
     * Actualiza la fecha del último login
     */
    public void registrarLogin() {
        this.ultimoLogin = new Date();
    }

    /**
     * Obtiene la dirección principal del usuario
     */
    public Direccion getDireccionPrincipal() {
        for (Direccion direccion : this.direcciones) {
            if (direccion.getEsPrincipal()) {
                return direccion;
            }
        }
        return null;
    }

    /**
     * Verifica si el usuario necesita cambiar su contraseña
     */
    public boolean requiereCambioPassword() {
        return this.cambioPassword != null && !this.cambioPassword;
    }
}
