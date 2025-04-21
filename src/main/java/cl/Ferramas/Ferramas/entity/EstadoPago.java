package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado_pago")
public class EstadoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long estadoPagoId;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "estadoPago", fetch = FetchType.LAZY)
    private List<Pago> pagos = new ArrayList<>();

    public EstadoPago(Long estadoPagoId, String nombre, List<Pago> pagos) {
        this.estadoPagoId = estadoPagoId;
        this.nombre = nombre;
        this.pagos = pagos;
    }

    public EstadoPago() {
    }

    public Long getEstadoPagoId() {
        return estadoPagoId;
    }

    public void setEstadoPagoId(Long estadoPagoId) {
        this.estadoPagoId = estadoPagoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
