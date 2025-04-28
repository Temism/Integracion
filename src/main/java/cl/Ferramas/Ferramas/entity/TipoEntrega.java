package cl.Ferramas.Ferramas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tipo_entrega")
public class TipoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoEntregaId;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tipoEntrega")
    private List<Pedido> pedidos = new ArrayList<>();


    public TipoEntrega(Long id, String nombre, String descripcion, List<Pedido> pedidos) {
        this.tipoEntregaId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pedidos = pedidos;
    }

    public TipoEntrega() {
    }


    public Long getTipoEntregaId() {
        return tipoEntregaId;
    }

    public void setTipoEntregaId(Long tipoEntregaId) {
        this.tipoEntregaId = tipoEntregaId;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
