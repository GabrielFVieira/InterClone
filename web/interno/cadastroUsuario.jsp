<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Cadastro de Usuário</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <jsp:include page="/interno/sidebar.jsp" />
        <section class="home-section">
            <%@page import="aplicacao.Usuario"%>
            <% Usuario usuario = (Usuario)request.getAttribute("usuario"); %>

            <form id="form" class="main-form" action="usuarios" method="post">
                <h2 class="text-center mb-2">Cadastrar Usuário</h2>

                <a href="usuarios" id="btnCancel">
                        <img src="images/icons/close.png" alt="Cancelar" title="Cancelar" />
                </a>

                <% if(usuario.getId() != null) { %>
                    <input type="hidden" class="form-control" name="id" value="<%= usuario.getId() %>">
                <% } %>
                
                <div class="row mb-3 mt-4">
                    <div class="col-5">
                        <div class="form-group">
                            <label for="cpf">CPF:</label>
                            <input type="text" class="cpf form-control" name="cpf" id="cpf" placeholder="000.000.000-00" value="<%= usuario.getCpf()%>" />
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="name">Nome:</label>
                            <input type="text" class="form-control" name="name" id="name" maxlength="20" value="<%= usuario.getNome()%>" />
                        </div>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col">
                        <div class="form-group">
                            <label for="password">Senha:</label>
                            <input type="password" class="form-control" name="password" id="password" value="<%= usuario.getSenha()%>" />
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="confirmPassword">Confirmar senha:</label>
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" value="<%= usuario.getSenha()%>" />
                        </div>
                    </div>
                </div>
                <% if(usuario.getId() != null) { %>
                    <div class="row mb-4">
                        <div class="col">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="suspended" name="suspended" value="true" <%= usuario.getSuspensoBoolean() ? "checked" : "" %> />
                                <label class="form-check-label" for="suspended">Suspender acesso ao sistema</label>
                            </div>
                        </div>
                    </div>
                <% } %>    
                <button id="btnSubmit" type="submit" class="btn btn-success"><%= usuario.getId() != null ? "Salvar alterações" : "Cadastrar" %></button>
            </form>
        </section>

        <%@include file="/base_scripts.html" %>
        <script src="js/jquery.mask.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/additional-methods.min.js"></script>
        <script src="js/localization/messages_pt_BR.js"></script>

        <script>
            $(document).ready(function () {
                $('#form').validate({
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
                    // submitHandler: function (form) {
                    // 	alert('Cadastro do ' + $(form).find('input[name="name"]').val() + ' realizado');
                    // 	$.ajax({
                    // 		url: 'cadastroUsuario',
                    // 		type: 'POST',
                    // 		datatype: 'json',
                    // 		data: data,
                    // 		success: function (data) {
                    // 			alert(data);
                    // 		},
                    // 	});
                    // },
                });

                $('.cpf').mask('000.000.000-00', { reverse: true });
            });
        </script>
    </body>
</html>
