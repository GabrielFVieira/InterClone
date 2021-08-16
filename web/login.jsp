<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Interclone</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        
        <div class="login-page">
            <form id="form" class="login-form" action="login" method="post">
                <h2 class="text-center">Credenciais inválidas, tente novamente</h2>
                
                <div class="form-group">
                    <label for="cpf">CPF:</label>
                    <input type="text" class="cpf form-control" name="cpf" id="cpf" placeholder="000.000.000-00" />
                </div>

                <div class="form-group">
                    <label for="password">Senha:</label>
                    <input type="password" class="form-control" name="password" id="password" />
                </div>

                <button id="btnSubmit" type="submit" class="btn btn-success mt-3">Cadastrar</button>
            </form>
        </div>
        
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
                        cpf: {
                            required: true,
                            cpfBR: true,
                        },
                        password: {
                            required: true,
                            minlength: 3,
                        },
                    },
                });

                $('.cpf').mask('000.000.000-00', { reverse: true });
            });
        </script>
    </body>
</html>
