package aplicacao;

import model.AdminDAO;
import model.UsuarioDAO;

public class Validador {
    public static final String ALERTA = "alerta";
    public static final String ERROS = "erros";
    private static final String MSG_CPF_INVALIDO = "CPF Inválido";
    public static final String MSG_CAMPO_OBRIGATORIO = "Campo obrigatório";
    private static final int MIN_CARACTERES_SENHA = 3;
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static AdminDAO adminDAO = new AdminDAO();
    
    public static void validarCPF(String cpf) {     
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException(MSG_CAMPO_OBRIGATORIO);
        }
        
        cpf = cpf.replace(".", "").replace("-", "");
        
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.length() != 11 ||
            cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999")) {
            throw new IllegalArgumentException(MSG_CPF_INVALIDO);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char)(r + 48);
            } // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char)(r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 != cpf.charAt(9)) || (dig11 != cpf.charAt(10))) {
                throw new Exception();
            }
        } catch (Exception erro) {
            throw new IllegalArgumentException(MSG_CPF_INVALIDO);
        }
    }
    
    public static void validarCampoNumerico(Number valor, boolean permitirZero) {
        if(valor == null) {
            throw new IllegalArgumentException(MSG_CAMPO_OBRIGATORIO);
        }
        
        if(valor.longValue() == 0) {
            throw new IllegalArgumentException("Preencha um valor diferente de zero");
        }
    }
    
    public static void validarCampoTexto(String valor, Integer tamanhoMinimo, Integer tamanhoMaximo) {
        validarCampoTexto(valor, tamanhoMinimo, tamanhoMaximo, true);
    }
    
    public static void validarCampoTexto(String valor, Integer tamanhoMinimo, Integer tamanhoMaximo, boolean obrigatorio) {
        if((valor == null || valor.isEmpty()) && obrigatorio) {
            throw new IllegalArgumentException(MSG_CAMPO_OBRIGATORIO);
        }
        
        if(valor != null) {
            if(tamanhoMinimo != null && valor.length() < tamanhoMinimo) {
                throw new IllegalArgumentException("Mínimo de " + tamanhoMinimo + " caracteres");
            }

            if(tamanhoMaximo != null && valor.length() > tamanhoMaximo) {
                throw new IllegalArgumentException("Máximo de " + tamanhoMinimo + " caracteres");
            }
        }
    }
    
    
    public static void validarSenha(String senha) {
        validarCampoTexto(senha, MIN_CARACTERES_SENHA, null);
    }
    
    public static void validarNovoCPF(String cpf) {
        validarCPF(cpf);
        
        if(usuarioDAO.buscarPorCPF(cpf) != null || adminDAO.buscarPorCPF(cpf) != null) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
    }
}
