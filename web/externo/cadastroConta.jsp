<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Cadastro de Categorias</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="aplicacao.Bancos"%>
        <jsp:include page="sidebar.jsp" />
        <section class="home-section">
            <%@page import="aplicacao.Conta"%>
            <% Conta conta = (Conta)request.getAttribute("conta"); %>
            
             <% String pos = request.getParameter("pos") == null ? "" : "?pos=" + request.getParameter("pos"); %>
            
            <form id="form" class="main-form" action="contas<%= pos %>" method="post">
                <h2 class="text-center mb-2"><%= conta.getId() != null ? "Editar" : "Cadastrar" %> Conta</h2>
               
                <a onClick="gerarURL('contas<%= pos %>')" id="btnCancel">
                    <img src="images/icons/close.png" alt="Cancelar" title="Cancelar" />
                </a>
                
                <% if(conta.getId() != null) { %>
                    <input type="hidden" class="form-control" name="id" value="<%= conta.getId() %>">
                <% } %>

                <div class="row mb-3 mt-4">
                    <div class="col">
                        <div class="form-group">
                            <label for="name">Nome:</label>
                            <input type="text" class="form-control" name="name" id="name"
                                placeholder="Digite um apelido para a conta corrente" maxlength="20"
                                value="<%= conta.getNome()%>"/>
                        </div>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col">
                        <div class="form-group">
                            <label for="bank">Banco:</label>
                            <input type="text" class="form-control" name="bank" id="bank" onchange="getBankName()" value="<%= conta.getBanco() %>" />
                        </div>
                        <p id="bankName"><%= conta.getNomeBanco() != null ? conta.getNomeBanco() : "" %></p>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="agency">Agência:</label>
                            <input type="text" class="form-control" name="agency" id="agency" value="<%= conta.getAgencia() %>" />
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="account">Conta-corrente:</label>
                            <input type="text" class="form-control" name="account" id="account" value="<%= conta.getContaCorrente() %>" />
                        </div>
                    </div>
                </div>
                <jsp:include page="/componentes/alert_error.jsp" />
                <button id="btnSubmit" type="submit" class="btn btn-success">Cadastrar</button>
            </form>
        </section>

        <%@include file="/base_scripts.html" %>
        <script src="js/jquery.mask.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/additional-methods.min.js"></script>
        <script src="js/localization/messages_pt_BR.js"></script>
        <script>
            const data = <%=Bancos.getBancosJson()%>;
            const bankMaxLength = 3;
            
            $(document).ready(function () {                    
                const validator = $('#form').validate({
                    errorPlacement: function (label, element) {
                        label.addClass('error-msg text-danger');
                        label.insertAfter(element);
                    },
                    wrapper: 'span',
                    rules: {
                        name: {
                            required: true,
                            maxlength: 20,
                        },
                        bank: {
                            required: true,
                            maxlength: bankMaxLength,
                        },
                        agency: {
                            required: true,
                            maxlength: 6,
                        },
                        account: {
                            required: true,
                            maxlength: 6,
                        },
                    },
                });
                
                $('#bank').mask('000');
                $('#agency').mask('#####0');
                $('#account').mask('0000-0');
                
                <jsp:include page="/componentes/form_error.jsp" />
            });
            
            function getBankName() {
                const bankId = document.getElementById('bank').value;
                const bankData = data.find(x => x.value == bankId);
                
                let bankName = '';
                if(bankData) {
                    bankName = bankData.label;
                } else if(bankId.length >= bankMaxLength) {
                    bankName = '<%=Bancos.BANCO_FICTICIO%>';
                }
                
                const bankNameText = document.getElementById('bankName')
                bankNameText.innerHTML = bankName;
            }
        </script>
    </body>
</html>
