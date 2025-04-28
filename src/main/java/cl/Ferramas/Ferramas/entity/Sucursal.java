package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String direccion;
    @Column(name = "telefono_sucursal")
    private String telefono;
    @Column(name = "email_sucursal")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comuna_id", nullable = false)
    @JsonBackReference
    private Comuna comuna;


    @OneToMany(mappedBy = "sucursal")
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "sucursal")
    private List<Inventario> inventarios = new ArrayList<>();

    @OneToMany(mappedBy = "sucursal")
    private List<Pedido> pedidos = new ArrayList<>();


    public Sucursal(Long sucursalId, String nombre, String direccion,  String telefono, String email,  Comuna comuna, List<Usuario> usuarios, List<Inventario> inventarios, List<Pedido> pedidos) {
        this.sucursalId = sucursalId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.comuna = comuna;
        this.usuarios = usuarios;
        this.inventarios = inventarios;
        this.pedidos = pedidos;
    }

    public Sucursal() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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



    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
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




}
