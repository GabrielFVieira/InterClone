/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.util.HashMap;
import java.util.Map;
public class Bancos {
    private static Map<String, String> bancos;
    public static final String BANCO_FICTICIO = "Banco fictício";
    static {
        bancos = new HashMap<>();
        bancos.put("001", "Banco do Brasil S.A.");
        bancos.put("003", "Banco da Amazônia S.A.");
        bancos.put("004", "Banco do Nordeste do Brasil S.A.");
        bancos.put("007", "Banco Nacional de Desenvolvimento Econômico e Social BNDES");
        bancos.put("010", "Credicoamo Crédito Rural Cooperativa");
        bancos.put("011", "Credit Suisse Hedging-Griffo Corretora de Valores S.A.");
        bancos.put("012", "Banco Inbursa S.A.");
        bancos.put("014", "Natixis Brasil S.A. Banco Múltiplo");
        bancos.put("015", "UBS Brasil Corretora de Câmbio, Títulos e Valores Mobiliários S.A.");
        bancos.put("016", "Coop de Créd. Mútuo dos Despachantes de Trânsito de SC e Rio Grande do Sul");
        bancos.put("017", "BNY Mellon Banco S.A.");
        bancos.put("018", "Banco Tricury S.A.");
        bancos.put("021", "Banestes S.A. Banco do Estado do Espírito Santo");
        bancos.put("024", "Banco Bandepe S.A.");
        bancos.put("025", "Banco Alfa S.A.");
        bancos.put("029", "Banco Itaú Consignado S.A.");
        bancos.put("033", "Banco Santander (Brasil) S. A.");
        bancos.put("036", "Banco Bradesco BBI S.A.");
        bancos.put("037", "Banco do Estado do Pará S.A.");
        bancos.put("040", "Banco Cargill S.A.");
        bancos.put("041", "Banco do Estado do Rio Grande do Sul S.A.");
        bancos.put("047", "Banco do Estado de Sergipe S.A.");
        bancos.put("060", "Confidence Corretora de Câmbio S.A.");
        bancos.put("062", "Hipercard Banco Múltiplo S.A.");
        bancos.put("063", "Banco Bradescard S.A.");
        bancos.put("064", "Goldman Sachs do Brasil Banco Múltiplo S. A.");
        bancos.put("065", "Banco AndBank (Brasil) S.A.");
        bancos.put("066", "Banco Morgan Stanley S. A.");
        bancos.put("069", "Banco Crefisa S.A.");
        bancos.put("070", "Banco de Brasília S.A.");
        bancos.put("074", "Banco J. Safra S.A.");
        bancos.put("075", "Banco ABN Amro S.A.");
        bancos.put("076", "Banco KDB do Brasil S.A.");
        bancos.put("077", "Banco Inter S.A.");
        bancos.put("078", "Haitong Banco de Investimento do Brasil S.A.");
        bancos.put("079", "Banco Original do Agronegócio S.A.");
        bancos.put("080", "BT Corretora de Câmbio Ltda.");
        bancos.put("081", "BBN Banco Brasileiro de Negocios S.A.");
        bancos.put("082", "Banco Topazio S.A.");
        bancos.put("083", "Banco da China Brasil S.A.");
        bancos.put("084", "Uniprime Norte do Paraná - Cooperativa de Crédito Ltda.");
        bancos.put("085", "Cooperativa Central de Crédito Urbano - Cecred");
        bancos.put("089", "Cooperativa de Crédito Rural da Região da Mogiana");
        bancos.put("091", "Central de Cooperativas de Economia e Crédito Mútuo do Est RS - Unicred");
        bancos.put("092", "BRK S.A. Crédito, Financiamento e Investimento");
        bancos.put("093", "Pólocred Sociedade de Crédito ao Microempreendedor e à Empresa de Pequeno Porte");
        bancos.put("094", "Banco Finaxis S.A.");
        bancos.put("095", "Banco Confidence de Câmbio S.A.");
        bancos.put("096", "Banco BMFBovespa de Serviços de Liquidação e Custódia S/A");
        bancos.put("097", "Cooperativa Central de Crédito Noroeste Brasileiro Ltda - CentralCredi");
        bancos.put("098", "Credialiança Cooperativa de Crédito Rural");
        bancos.put("099", "Uniprime Central – Central Interestadual de Cooperativas de Crédito Ltda.");
        bancos.put("100", "Planner Corretora de Valores S.A.");
        bancos.put("101", "Renascença Distribuidora de Títulos e Valores Mobiliários Ltda.");
        bancos.put("102", "XP Investimentos Corretora de Câmbio Títulos e Valores Mobiliários S.A.");
        bancos.put("104", "Caixa Econômica Federal");
        bancos.put("105", "Lecca Crédito, Financiamento e Investimento S/A");
        bancos.put("107", "Banco Bocom BBM S.A.");
        bancos.put("108", "PortoCred S.A. Crédito, Financiamento e Investimento");
        bancos.put("111", "Oliveira Trust Distribuidora de Títulos e Valores Mobiliários S.A.");
        bancos.put("113", "Magliano S.A. Corretora de Cambio e Valores Mobiliarios");
        bancos.put("114", "Central Cooperativa de Crédito no Estado do Espírito Santo - CECOOP");
        bancos.put("117", "Advanced Corretora de Câmbio Ltda.");
        bancos.put("118", "Standard Chartered Bank (Brasil) S.A. Banco de Investimento");
        bancos.put("119", "Banco Western Union do Brasil S.A.");
        bancos.put("120", "Banco Rodobens SA");
        bancos.put("121", "Banco Agibank S.A.");
        bancos.put("122", "Banco Bradesco BERJ S.A.");
        bancos.put("124", "Banco Woori Bank do Brasil S.A.");
        bancos.put("125", "Brasil Plural S.A. Banco Múltiplo");
        bancos.put("126", "BR Partners Banco de Investimento S.A.");
        bancos.put("127", "Codepe Corretora de Valores e Câmbio S.A.");
        bancos.put("128", "MS Bank S.A. Banco de Câmbio");
        bancos.put("129", "UBS Brasil Banco de Investimento S.A.");
        bancos.put("130", "Caruana S.A. Sociedade de Crédito, Financiamento e Investimento");
        bancos.put("131", "Tullett Prebon Brasil Corretora de Valores e Câmbio Ltda.");
        bancos.put("132", "ICBC do Brasil Banco Múltiplo S.A.");
        bancos.put("133", "Confederação Nacional das Cooperativas Centrais de Crédito e Economia Familiar e");
        bancos.put("134", "BGC Liquidez Distribuidora de Títulos e Valores Mobiliários Ltda.");
        bancos.put("135", "Gradual Corretora de Câmbio, Títulos e Valores Mobiliários S.A.");
        bancos.put("136", "Confederação Nacional das Cooperativas Centrais Unicred Ltda – Unicred do Brasil");
        bancos.put("137", "Multimoney Corretora de Câmbio Ltda");
        bancos.put("138", "Get Money Corretora de Câmbio S.A.");
        bancos.put("139", "Intesa Sanpaolo Brasil S.A. - Banco Múltiplo");
        bancos.put("140", "Easynvest - Título Corretora de Valores SA");
        bancos.put("142", "Broker Brasil Corretora de Câmbio Ltda.");
        bancos.put("143", "Treviso Corretora de Câmbio S.A.");
        bancos.put("144", "Bexs Banco de Câmbio S.A.");
        bancos.put("145", "Levycam - Corretora de Câmbio e Valores Ltda.");
        bancos.put("146", "Guitta Corretora de Câmbio Ltda.");
        bancos.put("149", "Facta Financeira S.A. - Crédito Financiamento e Investimento");
        bancos.put("157", "ICAP do Brasil Corretora de Títulos e Valores Mobiliários Ltda.");
        bancos.put("159", "Casa do Crédito S.A. Sociedade de Crédito ao Microempreendedor");
        bancos.put("163", "Commerzbank Brasil S.A. - Banco Múltiplo");
        bancos.put("169", "Banco Olé Bonsucesso Consignado S.A.");
        bancos.put("172", "Albatross Corretora de Câmbio e Valores S.A");
        bancos.put("173", "BRL Trust Distribuidora de Títulos e Valores Mobiliários S.A.");
        bancos.put("174", "Pernambucanas Financiadora S.A. Crédito, Financiamento e Investimento");
        bancos.put("177", "Guide Investimentos S.A. Corretora de Valores");
        bancos.put("180", "CM Capital Markets Corretora de Câmbio, Títulos e Valores Mobiliários Ltda.");
        bancos.put("182", "Dacasa Financeira S/A - Sociedade de Crédito, Financiamento e Investimento");
        bancos.put("183", "Socred S.A. - Sociedade de Crédito ao Microempreendedor");
        bancos.put("184", "Banco Itaú BBA S.A.");
        bancos.put("188", "Ativa Investimentos S.A. Corretora de Títulos Câmbio e Valores");
        bancos.put("189", "HS Financeira S/A Crédito, Financiamento e Investimentos");
        bancos.put("190", "Cooperativa de Economia e Crédito Mútuo dos Servidores Públicos Estaduais do Rio");
        bancos.put("191", "Nova Futura Corretora de Títulos e Valores Mobiliários Ltda.");
        bancos.put("194", "Parmetal Distribuidora de Títulos e Valores Mobiliários Ltda.");
        bancos.put("196", "Fair Corretora de Câmbio S.A.");
        bancos.put("197", "Stone Pagamentos S.A.");
        bancos.put("204", "Banco Bradesco Cartões S.A.");
        bancos.put("208", "Banco BTG Pactual S.A.");
        bancos.put("212", "Banco Original S.A.");
        bancos.put("213", "Banco Arbi S.A.");
        bancos.put("217", "Banco John Deere S.A.");
        bancos.put("218", "Banco BS2 S.A.");
        bancos.put("222", "Banco Credit Agrícole Brasil S.A.");
        bancos.put("224", "Banco Fibra S.A.");
        bancos.put("233", "Banco Cifra S.A.");
        bancos.put("237", "Banco Bradesco S.A.");
        bancos.put("241", "Banco Clássico S.A.");
        bancos.put("243", "Banco Máxima S.A.");
        bancos.put("246", "Banco ABC Brasil S.A.");
        bancos.put("249", "Banco Investcred Unibanco S.A.");
        bancos.put("250", "BCV - Banco de Crédito e Varejo S/A");
        bancos.put("253", "Bexs Corretora de Câmbio S/A");
        bancos.put("254", "Parana Banco S. A.");
        bancos.put("260", "Nu Pagamentos S.A.");
        bancos.put("265", "Banco Fator S.A.");
        bancos.put("266", "Banco Cédula S.A.");
        bancos.put("268", "Barigui Companhia Hipotecária");
        bancos.put("269", "HSBC Brasil S.A. Banco de Investimento");
        bancos.put("271", "IB Corretora de Câmbio, Títulos e Valores Mobiliários Ltda.");
        bancos.put("300", "Banco de la Nacion Argentina");
        bancos.put("318", "Banco BMG S.A.");
        bancos.put("320", "China Construction Bank (Brasil) Banco Múltiplo S/A");
        bancos.put("341", "Itaú Unibanco S.A.");
        bancos.put("366", "Banco Société Générale Brasil S.A.");
        bancos.put("370", "Banco Mizuho do Brasil S.A.");
        bancos.put("376", "Banco J. P. Morgan S. A.");
        bancos.put("389", "Banco Mercantil do Brasil S.A.");
        bancos.put("394", "Banco Bradesco Financiamentos S.A.");
        bancos.put("399", "Kirton Bank S.A. - Banco Múltiplo");
        bancos.put("412", "Banco Capital S. A.");
        bancos.put("422", "Banco Safra S.A.");
        bancos.put("456", "Banco MUFG Brasil S.A.");
        bancos.put("464", "Banco Sumitomo Mitsui Brasileiro S.A.");
        bancos.put("473", "Banco Caixa Geral - Brasil S.A.");
        bancos.put("477", "Citibank N.A.");
        bancos.put("479", "Banco ItauBank S.A.");
        bancos.put("487", "Deutsche Bank S.A. - Banco Alemão");
        bancos.put("488", "JPMorgan Chase Bank, National Association");
        bancos.put("492", "ING Bank N.V.");
        bancos.put("494", "Banco de La Republica Oriental del Uruguay");
        bancos.put("495", "Banco de La Provincia de Buenos Aires");
        bancos.put("505", "Banco Credit Suisse (Brasil) S.A.");
        bancos.put("545", "Senso Corretora de Câmbio e Valores Mobiliários S.A.");
        bancos.put("600", "Banco Luso Brasileiro S.A.");
        bancos.put("604", "Banco Industrial do Brasil S.A.");
        bancos.put("610", "Banco VR S.A.");
        bancos.put("611", "Banco Paulista S.A.");
        bancos.put("612", "Banco Guanabara S.A.");
        bancos.put("613", "Omni Banco S.A.");
        bancos.put("623", "Banco Pan S.A.");
        bancos.put("626", "Banco Ficsa S. A.");
        bancos.put("630", "Banco Intercap S.A.");
        bancos.put("633", "Banco Rendimento S.A.");
        bancos.put("634", "Banco Triângulo S.A.");
        bancos.put("637", "Banco Sofisa S. A.");
        bancos.put("641", "Banco Alvorada S.A.");
        bancos.put("643", "Banco Pine S.A.");
        bancos.put("652", "Itaú Unibanco Holding S.A.");
        bancos.put("653", "Banco Indusval S. A.");
        bancos.put("654", "Banco A. J. Renner S.A.");
        bancos.put("655", "Banco Votorantim S.A.");
        bancos.put("707", "Banco Daycoval S.A.");
        bancos.put("712", "Banco Ourinvest S.A.");
        bancos.put("719", "Banif - Bco Internacional do Funchal (Brasil) S.A.");
        bancos.put("735", "Banco Neon S.A.");
        bancos.put("739", "Banco Cetelem S.A.");
        bancos.put("741", "Banco Ribeirão Preto S.A.");
        bancos.put("743", "Banco Semear S.A.");
        bancos.put("745", "Banco Citibank S.A.");
        bancos.put("746", "Banco Modal S.A.");
        bancos.put("747", "Banco Rabobank International Brasil S.A.");
        bancos.put("748", "Banco Cooperativo Sicredi S. A.");
        bancos.put("751", "Scotiabank Brasil S.A. Banco Múltiplo");
        bancos.put("752", "Banco BNP Paribas Brasil S.A.");
        bancos.put("753", "Novo Banco Continental S.A. - Banco Múltiplo");
        bancos.put("754", "Banco Sistema S.A.");
        bancos.put("755", "Bank of America Merrill Lynch Banco Múltiplo S.A.");
        bancos.put("756", "Banco Cooperativo do Brasil S/A - Bancoob");
        bancos.put("757", "Banco Keb Hana do Brasil S. A.");
    }
    
    public static Map<String, String> getBancos() {
        return bancos;
    }
    
    public static String getBancosJson() {
        String json = "[";
        for (Map.Entry<String, String> entry : bancos.entrySet()) {
            json = json.concat(String.format("{'value':'%s', 'label':'%s'},\n", entry.getKey(), entry.getValue()));
        }
        json = json.substring(0, json.length() - 2);
        return json.concat("]");
    }
    
    public static String getNomeBanco(String banco) {
        if(bancos.containsKey(banco)) {
            return bancos.get(banco);
        }
        
        return BANCO_FICTICIO;
    }
}
