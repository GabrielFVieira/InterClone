<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Balancete</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="aplicacao.Conta"%>
        <%@page import="java.util.List"%>
        <%@page import="java.util.ArrayList"%>
        <%@page import="java.util.Map"%>
        
        <jsp:include page="sidebar.jsp" />
        <section class="home-section">
            <div class="main-form">
                <h2 class="text-center mb-3">Balancete</h2>
                
                <a onClick="gerarURL('lancamentos')" id="btnCancel">
                    <img src="images/icons/close.png" alt="Voltar" title="Voltar" />
                </a>
                
                <% final String GRAFICO = "grafico";
                   final String TABELA = "tabela";
                   final boolean isVazio = request.getAttribute("dados") == null || ((Map<String, Double>) request.getAttribute("dados")).isEmpty();
                   String tipo = request.getParameter("tipo") != null ? (String) request.getParameter("tipo") : GRAFICO; 
                   String novoTipo = GRAFICO.equals(tipo) ? TABELA : GRAFICO; %>
                
                <div class="d-flex justify-content-between mb-2">
                    <a class="table-button btn mr-2 text-white" onClick="onContaChange('<%= novoTipo %>');" style="margin-right: auto;">Alterar Visualização</a>
                    <div class="d-flex flex-row align-items-center" style="max-width: 250px; margin-left: auto;">
                        <label class="mb-0 mr-2" for="account">Conta: </label>
                        <select id="account" name="account" class="form-control text-truncate" onchange="onContaChange()">                           
                            <%
                            if(request.getAttribute("contas") != null) {
                                List<Conta> listaContas = (List<Conta>) request.getAttribute("contas");
                                String contaSelecionada = request.getParameter("conta"); %>
                                <option <%= contaSelecionada == null || "0".equals(contaSelecionada) ? "selected" : "" %> value="0">Todas</option>
                                <%
                                for (int i = 0; i < listaContas.size(); i++) {
                                    Conta conta = listaContas.get(i);
                            %>
                            <option <%= contaSelecionada != null && contaSelecionada.equals(conta.getId().toString()) ? "selected" : "" %> 
                                value="<%=conta.getId().toString() %>"><%=conta.getNome()%></option>
                            <%
                                }
                            }
                            %>
                        </select>
                    </div>
                </div>
                        
                <div class="chart-container mt-4">
                    <% if(!isVazio) {
                        if(TABELA.equals(tipo)) { %>
                            <jsp:include page="balancete/tabelaBalancete.jsp" />
                        <% } else { %>
                            <canvas id="myChart" />
                        <% }
                       } else { %>
                            <h4 class="mt-4 text-center">Nenhum lançamento realizado</h4> 
                       <% } %>
                </div>
            </div>
        </section>
        <%@include file="/base_scripts.html" %>
        <script>
            function gerarComplemento(tipo, conectivo) {
                return tipo === '' ? '' : conectivo + tipo;
            }
            function onContaChange(novoTipo) {
                const account = document.getElementById("account").value;

                let tipo = '<%= TABELA.equals(tipo) ? "tipo=" + TABELA : "" %>';
                if(novoTipo) {
                    if(novoTipo === '<%= GRAFICO %>') {
                        tipo = '';
                    } else {
                        tipo = 'tipo=' + novoTipo;   
                    }
                }
                
                if(account === '0') {
                    window.location.href = 'balancete' + gerarComplemento(tipo, '?');
                } else {
                    window.location.href = 'balancete?conta=' + account + gerarComplemento(tipo, '&');   
                }
            }
        </script>
        <% if(!isVazio && GRAFICO.equals(tipo)) { %>
            <jsp:include page="balancete/scriptsGraficoBalancete.jsp" />
        <% } %>        
    </body>
</html>
