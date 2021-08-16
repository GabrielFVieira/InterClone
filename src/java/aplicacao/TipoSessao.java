package aplicacao;

public enum TipoSessao {
    ADMNISTRADOR("Administrador"),
    USUARIO("Usuário"),
    LOGADO("");
    
    private String descricao;
    
    TipoSessao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
}
