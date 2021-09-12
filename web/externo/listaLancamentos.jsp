<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Lançamentos</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="java.time.LocalDate"%>
        <%@page import="java.time.format.DateTimeFormatter"%>
        <%@page import="java.util.ArrayList"%>
        <%@page import="aplicacao.Conta"%>
        <%@page import="aplicacao.Lancamento"%>
        <%@page import="aplicacao.TipoOperacao"%>
        <%@page import="java.util.List"%>
        <%@page import="java.util.Map"%>

        <jsp:include page="/externo/sidebar.jsp" />
        <section class="home-section">
            <div class="page-container">
                <h2>Lançamentos</h2>
                <% Double saldo = (Double) request.getAttribute("saldo");
                   String sinalSaldo = saldo < 0 ? "- " : ""; %>
                <div class="d-flex mb-2">
                    <div class="box">
                        <a class="table-button btn mr-2 text-white" onClick="gerarURL('lancamentos', 'cadastrar');">Novo Lançamento</a>
                        <a class="table-button btn btn-info customTooltip text-white" onClick="gerarURL('balancete');" style="margin-right: auto; border-radius: 50%;">
                            <i class='bx bx-bar-chart' ></i>
                            <span class="tooltiptext tooltip-top">Balancete</span>
                        </a>
                    </div>
                    <div class="box">
                        <div class="card ml-2 mr-2 border-0 <%= saldo < 0 ? "alert-danger" : "" %>" >
                            <div class="card-body text-center" style="padding: 8px !important;">
                                <p class="card-title mb-0"><b>Saldo:</b> <%= sinalSaldo + String.format("R$ %1$,.2f", Math.abs(saldo)) %></p>
                                <% if(saldo < 0) { %>
                                    <p class="card-text text-center mt-2">Sua conta está no negativo!</p>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <div class="box">
                        <div class="d-flex flex-row align-items-center" style="max-width: 250px; margin-left: auto;">
                            <label class="mb-0 mr-2" for="account">Conta: </label>
                            <select id="account" name="account" class="form-control text-truncate" onchange="onContaChange()">                           
                                <%
                                if(request.getAttribute("contas") != null) {
                                    ArrayList<Conta> listaContas = (ArrayList<Conta>) request.getAttribute("contas");
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
                </div>                
                <%
                if(request.getAttribute("lancamentos") != null) {
                    Map<LocalDate, List<Lancamento>> mapaLancamentos = (Map<LocalDate, List<Lancamento>>) request.getAttribute("lancamentos");
                    
                    if(mapaLancamentos.isEmpty()) { %>
                        <h4 class="mt-4">Nenhum lançamento realizado</h4>
                    <% }

                    for (Map.Entry<LocalDate, List<Lancamento>> en : mapaLancamentos.entrySet()) {
                        List<Lancamento> listaLancamentos = en.getValue();
                        for (int i = 0; i < listaLancamentos.size(); i++) {
                            Lancamento lancamento = listaLancamentos.get(i);
                            String cor = TipoOperacao.CREDITO.equals(lancamento.getOperacao()) ? "text-success" : "text-danger";
                            String sinal = TipoOperacao.CREDITO.equals(lancamento.getOperacao()) ? "+ " : "- ";
                %>
                            <div class="card mb-<%= i == listaLancamentos.size() - 1 ? "4" : "1" %>">
                                <% if(i == 0) { %>
                                <div class="card-header text-center">
                                    <%= en.getKey().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %>
                                </div>
                                <% } %>
                                <div class="d-flex justify-content-between align-items-stretch">
                                    <div class="card-body">
                                        <h5 class="card-title"><%= lancamento.getCategoria().getDescricao() %></h5>
                                        <p class="card-text"><%= lancamento.getDescricao()== null || lancamento.getDescricao().isEmpty() ? "(Sem descrição)" : lancamento.getDescricao() %></p>
                                    </div>
                                    <div class="card_menu">
                                        <div class="card_menu_left">
                                            <p class="<%= cor %>"><%= String.format(sinal + " R$ %1$,.2f", lancamento.getValor()) %></p>
                                            <p class="<%= cor %>" ><%= lancamento.getOperacao().getDescricao() %></p>
                                            <h6 class="card_menu-subtitle text-muted text-right"><%= lancamento.getConta().getNome()%></h6>
                                        </div>
                                        <div class="card_menu_right">
                                            <a onClick="gerarURL('lancamentos', 'editar', <%=lancamento.getId()%>);" class="btn customTooltip">
                                                <i class='bx bx-edit-alt text-primary'></i>
                                                <span class="tooltiptext tooltip-right">Editar</span>
                                            </a>
                                            <a onClick="gerarURL('lancamentos', 'excluir', <%=lancamento.getId()%>, 'conta', 'account');" class="btn customTooltip">
                                                <i class='bx bx-trash text-danger' ></i>
                                                <span class="tooltiptext tooltip-right">Remover</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                <%
                        }
                    }
                }
                %>		
            </div>
        </section>
        <%@include file="/base_scripts.html" %>
        <script>            
            function onContaChange() {
                const account = document.getElementById("account").value;
                
                if(account === '0') {
                    window.location.href = "lancamentos";
                } else {
                    window.location.href = "lancamentos?conta=" + account;   
                }
            }
        </script>
    </body>
</html>
