<%@page import="aplicacao.TipoSessao"%>
<%@page import="aplicacao.Session"%>        
<li class="profile">
    <div class="profile-details">
        <img src="images/profile.png" alt="profileImg" />
        <div class="name_job">
            <% Session sessaoCorrente = (Session) session.getAttribute("session"); %>
            <div class="name"><%= sessaoCorrente.getNomeUsuario() %></div>
            <div class="job"><%= sessaoCorrente.getTipo().getDescricao()%></div>
        </div>
    </div>
    <a href="logout">
        <i class="bx bx-log-out" id="logout"></i>
        <span class="tooltip">Sair</span>
    </a>
</li>