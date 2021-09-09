package aplicacao;

public enum TipoOperacao {
    CREDITO("C", "Crédito"),
    DEBITO("D", "Débito");
    
    private String id;
    private String descricao;
    
    TipoOperacao(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public static TipoOperacao convert(String id) {
        for(TipoOperacao tipo : TipoOperacao.values()) {
            if(tipo.getId().equals(id.toUpperCase())) {
                return tipo;
            }
        }
        
        return null;
    }
}
