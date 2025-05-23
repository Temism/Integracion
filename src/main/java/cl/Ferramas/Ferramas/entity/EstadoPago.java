package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado_pago")
public class EstadoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadoPagoId;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "estado")
    private List<Pago> pagos = new ArrayList<>();


    public EstadoPago(Long estadoPagoId, String nombre, String descripcion, List<Pago> pagos) {
        this.estadoPagoId = estadoPagoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
