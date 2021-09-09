package aplicacao;


public class Session {
    private Integer idUsuario;
    private String nomeUsuario;
    private TipoSessao tipo;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = formatarNomeUsuario(nomeUsuario);
    }

    public TipoSessao getTipo() {
        return tipo;
    }

    public void setTipo(TipoSessao tipo) {
        this.tipo = tipo;
    }
    
    private String formatarNomeUsuario(String string){
        StringBuffer sb=new StringBuffer(string);
        for(int i=0;i<sb.length();i++) {
            if(i==0 || sb.charAt(i-1)==' ') {
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            }  
        }
        return sb.toString();
    }
}
