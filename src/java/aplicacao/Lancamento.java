package aplicacao;

import java.time.LocalDate;

public class Lancamento {
    private Integer id;
    private Conta conta;
    private Categoria categoria;
    private Double valor;
    private TipoOperacao operacao;
    private LocalDate data;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }

    public String getOperacaoString() {
        return operacao.getId();
    }
    
    public void setOperacao(TipoOperacao operacao) {
        this.operacao = operacao;
    }

    public void setOperacao(String charOperacao) {
        this.operacao = TipoOperacao.convert(charOperacao);
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
