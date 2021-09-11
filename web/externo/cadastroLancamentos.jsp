<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Cadastro de Lançamentos</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <jsp:include page="/externo/sidebar.jsp" />
        <section class="home-section">
            <%@page import="java.util.ArrayList"%>
            <%@page import="aplicacao.Lancamento"%>
            <%@page import="aplicacao.Conta"%>
            <%@page import="aplicacao.Categoria"%>
            <%@page import="aplicacao.TipoOperacao"%>
            <%@page import="java.util.Iterator"%> 
        
            <% Lancamento lancamento = (Lancamento)request.getAttribute("lancamento"); %>

            <form id="form" class="main-form" action="lancamentos" method="post">
                <h2 class="text-center mb-2"><%= lancamento.getId() != null ? "Editar" : "Cadastrar" %> Lançamento</h2>

                <a onClick="gerarURL('lancamentos')" id="btnCancel">
                    <img src="images/icons/close.png" alt="Cancelar" title="Cancelar" />
                </a>

                <% if(lancamento.getId() != null) { %>
                    <input type="hidden" class="form-control" name="id" value="<%= lancamento.getId() %>">
                <% } %>

                <div class="row mb-1 mt-4">
                    <div class="form-group col">
                        <label for="account">Conta-Corrente:</label>
                        <select id="account" name="account" class="form-control">
                            <option disabled <%=lancamento.getConta() == null ? "selected" : "" %> value>- Selecione uma opção -</option>
                            <%
                            if(request.getAttribute("contas") != null) {
                                ArrayList<Conta> listaContas = (ArrayList<Conta>) request.getAttribute("contas");
                                for (int i = 0; i < listaContas.size(); i++) {
                                    Conta conta = listaContas.get(i);
                            %>
                            <option <%= lancamento.getConta() != null && lancamento.getConta().getId().equals(conta.getId()) 
                                    ? "selected" : "" %> value="<%=conta.getId()%>"><%=conta.getNome()%></option>
                            <%
                                }
                            }
                            %>
                        </select>
                    </div>
                </div>
                <div class="row mb-1">
                    <div class="form-group col">
                        <label for="category">Categoria:</label>
                        <select id="category" name="category" class="form-control">
                            <option disabled <%=lancamento.getCategoria() == null ? "selected" : "" %> value>- Selecione uma opção -</option>
                            <%
                            if(request.getAttribute("categorias") != null) {
                                ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                                for (int i = 0; i < listaCategorias.size(); i++) {
                                    Categoria categoria = listaCategorias.get(i);
                            %>
                            <option <%= lancamento.getCategoria() != null && lancamento.getCategoria().getId().equals(categoria.getId()) 
                                    ? "selected" : "" %> value="<%=categoria.getId()%>"><%=categoria.getDescricao()%></option>
                            <%
                                }
                            }
                            %>
                        </select>
                    </div>
                    <div class="form-group col">
                        <label for="operation">Operação:</label>
                        <select id="operation" name="operation" class="form-control">
                            <option disabled <%=lancamento.getOperacao() == null ? "selected" : "" %> value>- Selecione uma opção -</option>
                            <%
                            for (int i = 0; i < TipoOperacao.values().length; i++) {
                                TipoOperacao operacao = TipoOperacao.values()[i];
                            %>
                            <option <%= lancamento.getOperacao() != null && lancamento.getOperacao().getId().equals(operacao.getId()) 
                                    ? "selected" : "" %> value="<%=operacao.getId()%>"><%=operacao.getDescricao()%></option>
                            <%
                            }
                            %>
                        </select>
                    </div>
                </div>
                <div class="row mb-1">
                    <div class="form-group col">
                        <label for="value">Valor:</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">R$</div>
                            </div>
                            <input type="text" class="form-control" name="value" id="value" placeholder="000,00"
                             value="<%= lancamento.getValor() == null ? "" : String.format("%.2f", lancamento.getValor()) %>"/>
                        </div>
                    </div>
                    <div class="form-group col">
                        <label for="date">Data:</label>
                        <input type="date" class="form-control" name="date" id="date" 
                         value="<%= lancamento.getData() == null ? "" : lancamento.getData().toString() %>"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="form-group col">
                        <div class="form-group">
                            <div class="d-flex">
                                <label for="description">Descrição: </label>
                                <p class="text-muted ml-1">(máximo de 100 caracteres)</p>
                            </div>
                            <textarea class="form-control" name="description" id="description" rows="3" maxlength="100" 
                            ><%= lancamento.getDescricao()== null ? "" : lancamento.getDescricao() %></textarea>
                        </div>
                    </div>
                </div>                
                        
                <jsp:include page="/componentes/alert_error.jsp" />
                <button id="btnSubmit" type="submit" class="btn btn-success mt-2"><%= lancamento.getId() != null ? "Salvar alterações" : "Cadastrar" %></button>
            </form>
        </section>

        <%@include file="/base_scripts.html" %>
        <script src="js/jquery.mask.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/additional-methods.min.js"></script>
        <script src="js/localization/messages_pt_BR.js"></script>

        <script>
            $(document).ready(function () {
                const validator = $('#form').validate({
                    errorPlacement: function (label, element) {
                        label.addClass('error-msg text-danger');
                        
                        if(element[0].id === 'value') {
                            label.insertAfter(element[0].parentElement);
                        } else {
                            label.insertAfter(element);
                        }
                    },
                    wrapper: 'span',
                    rules: {
                        account: {
                            required: true,
                        },
                        category: {
                            required: true,
                        },
                        operation: {
                            required: true,
                        },
                        value: {
                            required: true,
                        },
                        date: {
                            required: true,
                            date: true,
                        },
                        description: {
                            maxlength: 100,
                        },
                    }
                });

                $('#value').mask('0.000.000.000,00', { reverse: true });
                <jsp:include page="/componentes/form_error.jsp" />
            });
        </script>
    </body>
</html>
