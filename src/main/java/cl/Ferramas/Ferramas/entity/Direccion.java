package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long direccionId;
    @Column( nullable = false)
    private String calle;
    @Column( nullable = false)
    private Long numero;

    private String dpto;

    private String codigoPostal;

    private Boolean esPrincipal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "direccionEntrega", fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();


    public Direccion(Long id, String calle, Long numero, String dpto, String codigoPostal, Boolean esPrincipal, Usuario usuario, Comuna comuna, Ciudad ciudad, Region region, List<Pedido> pedidos) {
        this.direccionId = id;
        this.calle = calle;
        this.numero = numero;
        this.dpto = dpto;
        this.codigoPostal = codigoPostal;
        this.esPrincipal = esPrincipal;
        this.usuario = usuario;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.region = region;
        this.pedidos = pedidos;
    }

    public Direccion() {
    }

    public Long getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Long direccionId) {
        this.direccionId = direccionId;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // MÉTODOS HELPER

    /**
     * Obtiene la dirección completa en formato legible
     */
    public String getDireccionCompleta() {
        StringBuilder direccion = new StringBuilder();
        if (this.calle != null) {
            direccion.append(this.calle).append(" ");
        }

        if (this.numero != null) {
            direccion.append(this.numero);
        }

        if (this.dpto != null && !this.dpto.trim().isEmpty()) {
            direccion.append(", Depto/Of. ").append(this.dpto);
        }

        if (this.comuna != null && this.comuna.getNombre() != null) {
            direccion.append(", ").append(this.comuna.getNombre());
        }

        if (this.ciudad != null && this.ciudad.getNombre() != null) {
            direccion.append(", ").append(this.ciudad.getNombre());
        }

        if (this.region != null && this.region.getNombre() != null) {
            direccion.append(", ").append(this.region.getNombre());
        }

        if (this.codigoPostal != null && !this.codigoPostal.trim().isEmpty()) {
            direccion.append(", CP: ").append(this.codigoPostal);
        }

        return direccion.toString();
    }

    /**
     * Establece esta dirección como la principal y actualiza las demás
     */
    public void establecerComoPrincipal() {
        // Primero debería establecer todas las demás direcciones como no principales
        if (this.usuario != null) {
            for (Direccion otraDireccion : this.usuario.getDirecciones()) {
                if (!otraDireccion.getDireccionId().equals(this.direccionId)) {
                    otraDireccion.setEsPrincipal(false);
                }
            }
        }

        this.esPrincipal = true;
    }

    /**
     * Valida si la dirección tiene todos los campos requeridos
     */
    public boolean esValida() {
        return this.calle != null && !this.calle.isEmpty() &&
                this.numero != null && this.numero > 0 &&
                this.comuna != null &&
                this.ciudad != null &&
                this.region != null;
    }
}
