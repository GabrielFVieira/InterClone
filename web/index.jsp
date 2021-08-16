<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>InterClone</title>
        <%@include file="/header.html" %>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <section class="firstSection">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light fixed-top p-0">
                    <div class="container-fluid">
                        <a class="customBrand" href="#" id="brand">InterClone</a>

                        <div class="justify-content-end" id="navbarNavDropdown">
                            <ul class="navbar-nav">
                                <li class="nav-item dropdown">
                                    <a
                                            class="nav-link dropdown-toggle"
                                            href="#"
                                            id="loginDropdown"
                                            role="button"
                                            data-toggle="modal"
                                            data-target="#exampleModalCenter"
                                            aria-haspopup="true"
                                            aria-expanded="false"
                                    >
                                            Entrar
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

                <!-- Modal -->
                <div
                    class="modal fade"
                    id="exampleModalCenter"
                    tabindex="-1"
                    role="dialog"
                    aria-labelledby="exampleModalCenterTitle"
                    aria-hidden="true"
                >
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Bem vindo!</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form id="form" action="login" method="post">
                                    <div class="form-group">
                                        <label for="cpf">CPF:</label>
                                        <input type="text" class="cpf form-control" name="cpf" id="cpf" placeholder="000.000.000-00" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Senha:</label>
                                        <input type="password" class="form-control" name="password" id="password" />
                                    </div>
                                    <div class="modal-footer">
                                        <input type="submit" value="Entrar" class="btn btn-danger mb-3"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-3 flex-row-reverse">
                    <div class="col-12 col-lg-6 text-center">
                        <img src="images/hero-banner-plano-de-saude.png" class="img-fluid" />
                    </div>

                    <div class="col-12 col-lg-6 d-flex justify-content-center align-items-center">
                        <div class="text-left">
                            <h1>Esse projeto tem como objetivo:</h1>

                            <p>Controle de lançamentos de créditos e débitos como uma conta corrente de um banco.</p>
                            <a href="#secondSection"><button class="customButton">Conheça mais sobre o projeto</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="secondSection">
            <div class="descricao d-flex justify-content-center align-items-center">
                <h1>Clique em 'Entrar' na barra de navegação para ter acesso admnistrativo ou como usuário</h1>
            </div>
        </section>

        <%@include file="/base_scripts.html" %>
        <script src="js/jquery.mask.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/additional-methods.min.js"></script>
        <script src="js/localization/messages_pt_BR.js"></script>
        <script>
            $(document).ready(function () {
                window.addEventListener('scroll', () => {
                    let windowPosition = window.scrollY > 1;
                    $(".navbar").toggleClass("scrolledNav", windowPosition);
                });
                
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

                $('#cpf').mask('000.000.000-00', { reverse: true });
            });
        </script>
    </body>
</html>
