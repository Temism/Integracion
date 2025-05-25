package cl.Ferramas.Ferramas.dto;

public class CambioEstadoDTO {
    private Long estadoNuevoId;
    private Long usuarioId;
    private String comentario;

    public CambioEstadoDTO() {}

    public Long getEstadoNuevoId() {
        return estadoNuevoId;
    }

    public void setEstadoNuevoId(Long estadoNuevoId) {
        this.estadoNuevoId = estadoNuevoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
