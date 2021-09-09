<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Cadastro de Categorias</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <jsp:include page="/interno/sidebar.jsp" />
        <section class="home-section">
            <%@page import="aplicacao.Categoria"%>
            <% Categoria categoria = (Categoria)request.getAttribute("categoria"); %>

            <form id="form" class="main-form" action="categorias" method="post">
                <h2 class="text-center mb-2"><%= categoria.getId() != null ? "Editar" : "Cadastrar" %> Categoria</h2>

                <a href="categorias" id="btnCancel">
                        <img src="images/icons/close.png" alt="Cancelar" title="Cancelar" />
                </a>

                <% if(categoria.getId() != null) { %>
                    <input type="hidden" class="form-control" name="id" value="<%= categoria.getId() %>">
                <% } %>
                
                <div class="row mb-3 mt-4">
                    <div class="col">
                        <div class="form-group">
                            <label for="name">Descrição:</label>
                            <input type="text" class="form-control" name="description" id="description" maxlength="20" value="<%= categoria.getDescricao()%>" />
                        </div>
                    </div>
                </div>

                <jsp:include page="/alert_error.jsp" />
                <button id="btnSubmit" type="submit" class="btn btn-success mt-2"><%= categoria.getId() != null ? "Salvar alterações" : "Cadastrar" %></button>
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
                        description: {
                            required: true,
                            maxlength: 20,
                        },
                    },
                });
                
                <jsp:include page="/form_error.jsp" />
            });
        </script>
    </body>
</html>
