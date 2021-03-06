<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone | Contas</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="java.util.ArrayList"%>
        <%@page import="aplicacao.Conta"%>

        <jsp:include page="sidebar.jsp" />
        <section class="home-section">
            <div class="page-container" style="margin: auto;">
                <jsp:include page="/componentes/alert_error.jsp" />
                <%  ArrayList<Conta> listaContas = null;
                    if(request.getAttribute("contas") != null) {
                        listaContas = (ArrayList<Conta>) request.getAttribute("contas");
                    }
                    int contaAnteriormenteAtiva = request.getParameter("pos") != null ? Integer.parseInt(request.getParameter("pos")) : 0;
                    int ultimaPosicao = listaContas == null ? 0 : listaContas.size() - 1;
                    
                    int contaAtiva = contaAnteriormenteAtiva == 0 ? 0 : (contaAnteriormenteAtiva > ultimaPosicao ? ultimaPosicao : contaAnteriormenteAtiva); %>
                <input type="hidden" id="account" value="<%= contaAtiva %>">
                
                <h2>Contas-Correntes</h2>
                
                <div class="d-flex align-content-center justify-content-center"> 
                    <a class="table-button btn text-white" onClick="gerarURL('contas', 'cadastrar');">Nova Conta-Corrente</a>    
                </div>
                
                <div class="d-flex align-content-center justify-content-center">
                    <% if(listaContas != null) {
                        if(listaContas.isEmpty()) { %>
                            <h4 class="mt-4 text-center">Nenhuma conta-corrente cadastrada</h4>
                        <% }%>

                    <div id="carousel" class="carousel slide" data-ride="carousel" data-interval="false">
                      <ol class="carousel-indicators">
                        <% for (int i = 0; i < listaContas.size(); i++) { %>
                            <li data-target="#carousel" style="filter: invert(100%);" data-slide-to="<%= i %>" <%= i == contaAtiva ? " class=\"active\" " : "" %>></li>
                        <% } %>
                      </ol>
                      <div class="carousel-inner">
                        <%
                            int colorCode = 0;
                            for (int i = 0; i < listaContas.size(); i++) {
                                Conta conta = listaContas.get(i);
                        %>
                            <div class="carousel-item <%= i == contaAtiva ? "active" : "" %>" >
                                <div class="card-container">
                                    <div class="outer">
                                        <div class="account-card 
                                           <% switch(colorCode) {
                                                case 1: %> 
                                                   card-purple 
                                                <% colorCode++;
                                                   break;
                                                case 2: %> 
                                                   card-red 
                                                <% colorCode = 0;
                                                   break;
                                                default:
                                                   colorCode++;
                                                   break;
                                            } %>
                                            ">
                                            <div class="card-header"></div>
                                            <div class="account-card-body-container">
                                                <div class="card-body">
                                                    <h5 class="card-title"><%=conta.getNome()%></h5>
                                                        <div class="d-flex flex-row justify-content-left">
                                                            <div class="account-card-field">
                                                                <p>Banco</p>
                                                                <h5><%=conta.getBanco()%></h5>
                                                            </div>
                                                            <div class="account-card-field">
                                                                <p>Ag??ncia</p>
                                                                <h5><%=conta.getAgencia()%></h5>
                                                            </div>
                                                            <div class="account-card-field">
                                                                <p>Conta</p>
                                                                <h5><%=conta.getContaCorrente()%></h5>
                                                            </div>
                                                        </div>
                                                    <p class="account-card-text"><%=conta.getNomeBanco()%></p>  
                                                </div>
                                                <div class="account-card-btn-container">
                                                    <a onClick="gerarURLPos('contas', 'editar', <%=conta.getId()%>, 'account');" class="btn customTooltip">
                                                        <i class='bx bx-edit-alt bx-md text-white'></i>
                                                        <span class="tooltiptext tooltip-top">Editar</span>
                                                    </a>
                                                    <a onClick="gerarURL('contas', 'excluir', <%=conta.getId()%>, 'pos', 'account');" class="btn customTooltip">
                                                        <i class='bx bx-trash bx-md text-white' ></i>
                                                        <span class="tooltiptext tooltip-top">Remover</span>
                                                    </a>  
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                      </div>
                      <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" style="filter: invert(100%);" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                      </a>
                      <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" style="filter: invert(100%);" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                      </a>
                    </div>   
                    <% } %>
                </div>
            </div>
        </section>
        <%@include file="/base_scripts.html" %>
        <script>   
            function gerarURLPos(page, acao, id, valorFiltro) {
                const valorF = document.getElementById(valorFiltro).value;
                return gerarURL(page, acao, id + '&pos=' + valorF);
            }
            
            $('.carousel').on('slide.bs.carousel',function(e){
                var slideTo = $(e.relatedTarget).index();
                $('#account').val(slideTo);
            });
        </script>
    </body>
</html>
