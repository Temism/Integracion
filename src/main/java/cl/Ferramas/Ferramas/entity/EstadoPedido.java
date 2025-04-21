package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "estado_pedido")
public class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long estadoPedidoId;

    @Column( nullable = false)
    private String nombre;


    private String descripcion;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<HistorialEstadoPedido> historialEstados = new ArrayList<>();


    public EstadoPedido(Long id, String nombre, String descripcion, List<Pedido> pedidos, List<HistorialEstadoPedido> historialEstados) {
        this.estadoPedidoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pedidos = pedidos;
        this.historialEstados = historialEstados;
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

    public List<HistorialEstadoPedido> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<HistorialEstadoPedido> historialEstados) {
        this.historialEstados = historialEstados;
    }
}

