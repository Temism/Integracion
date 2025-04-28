package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "estado_pedido")
public class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadoPedidoId;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "estado")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "estadoAnterior")

    private List<HistorialEstadoPedido> historialesAnteriores = new ArrayList<>();

    @OneToMany(mappedBy = "estadoNuevo")

    private List<HistorialEstadoPedido> historialesNuevos = new ArrayList<>();


    public EstadoPedido(Long estadoPedidoId, String nombre, String descripcion, List<Pedido> pedidos, List<HistorialEstadoPedido> historialesAnteriores, List<HistorialEstadoPedido> historialesNuevos) {
        this.estadoPedidoId = estadoPedidoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pedidos = pedidos;
        this.historialesAnteriores = historialesAnteriores;
        this.historialesNuevos = historialesNuevos;
    }

    public EstadoPedido() {
    }

    public Long getEstadoPedidoId() {
        return estadoPedidoId;
    }

    public void setEstadoPedidoId(Long estadoPedidoId) {
        this.estadoPedidoId = estadoPedidoId;
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

    public List<HistorialEstadoPedido> getHistorialesAnteriores() {
        return historialesAnteriores;
    }

    public void setHistorialesAnteriores(List<HistorialEstadoPedido> historialesAnteriores) {
        this.historialesAnteriores = historialesAnteriores;
    }

    public List<HistorialEstadoPedido> getHistorialesNuevos() {
        return historialesNuevos;
    }

    public void setHistorialesNuevos(List<HistorialEstadoPedido> historialesNuevos) {
        this.historialesNuevos = historialesNuevos;
    }
}

