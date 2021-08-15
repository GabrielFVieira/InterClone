package aplicacao;

public class Usuario {
    private Integer id;
    private String nome;
    private String cpf;
    private String senha;
    private Character suspenso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getSuspenso() {
        return suspenso;
    }
    
    public Boolean getSuspensoBoolean() {
        return suspenso != null && suspenso.equals('S') ? true : false;
    }

    public void setSuspenso(Character suspenso) {
        this.suspenso = suspenso;
    }
    
    public void setSuspenso(Boolean suspenso) {
        this.suspenso = suspenso ? 'S' : 'N';
    }
    
}
