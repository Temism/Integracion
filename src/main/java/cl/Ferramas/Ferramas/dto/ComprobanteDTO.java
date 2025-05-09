package cl.Ferramas.Ferramas.dto;

public class ComprobanteDTO {
    private Long pedidoId;
    private String urlComprobante;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getUrlComprobante() {
        return urlComprobante;
    }

    public void setUrlComprobante(String urlComprobante) {
        this.urlComprobante = urlComprobante;
    }
}
