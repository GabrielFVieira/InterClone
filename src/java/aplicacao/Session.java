package aplicacao;


public class Session {
    private String nomeUsuario;
    private TipoSessao tipo;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public TipoSessao getTipo() {
        return tipo;
    }

    public void setTipo(TipoSessao tipo) {
        this.tipo = tipo;
    }
}
