<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Cadastro de Administrador</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <jsp:include page="/interno/sidebar.jsp" />
        <section class="home-section">
            <%@page import="aplicacao.Administrador"%>
            <% Administrador admin = (Administrador)request.getAttribute("admin"); %>

            <form id="form" class="main-form" action="administradores" method="post">
                <h2 class="text-center mb-2"><%= admin.getId() != null ? "Editar" : "Cadastrar" %> Administrador</h2>

                <a onClick="gerarURL('administradores')" id="btnCancel">
                    <img src="images/icons/close.png" alt="Cancelar" title="Cancelar" />
                </a>

                <% if(admin.getId() != null) { %>
                    <input type="hidden" class="form-control" name="id" value="<%= admin.getId() %>">
                <% } %>
                
                <div class="row mb-3 mt-4">
                    <div class="col-5">
                        <div class="form-group">
                            <label for="cpf">CPF:</label>
                            <input type="text" class="cpf form-control" name="cpf" id="cpf" placeholder="000.000.000-00" value="<%= admin.getCpf()%>" />
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="name">Nome:</label>
                            <input type="text" class="form-control" name="name" id="name" maxlength="20" value="<%= admin.getNome()%>" />
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <div class="form-group">
                            <label for="password">Senha:</label>
                            <input type="password" class="form-control" name="password" id="password" value="<%= admin.getSenha()%>" />
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="confirmPassword">Confirmar senha:</label>
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" value="<%= admin.getSenha()%>" />
                        </div>
                    </div>
                </div>
                        
                <jsp:include page="/componentes/alert_error.jsp" />
                <button id="btnSubmit" type="submit" class="btn btn-success mt-2"><%= admin.getId() != null ? "Salvar alterações" : "Cadastrar" %></button>
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
                        label.insertAfter(element);
                    },
                    wrapper: 'span',
                    rules: {
                        name: {
                            required: true,
                            maxlength: 20,
                        },
                        cpf: {
                            required: true,
                            cpfBR: true,
                        },
                        password: {
                            required: true,
                            minlength: 3,
                        },
                        confirmPassword: {
                            required: true,
                            equalTo: '#password',
                        },
                    },
                });
                
                <jsp:include page="/componentes/form_error.jsp" />

                $('.cpf').mask('000.000.000-00', { reverse: true });
            });
        </script>
    </body>
</html>
