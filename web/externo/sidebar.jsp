<%@page import="aplicacao.TipoSessao"%>
<%@page import="aplicacao.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <div class="logo-details">
        <i class="bx bxl-linkedin icon"></i>
        <div class="logo_name">InterClone</div>
        <i class="bx bx-menu" id="btn"></i>
    </div>
    <ul class="nav-list">
        <li>
            <a href="dashboard">
                <i class="bx bx-grid-alt"></i>
                <span class="links_name">Dashboard</span>
            </a>
            <span class="tooltip">Dashboard</span>
        </li>
        <li>
            <a href="#">
                <i class="bx bx-money"></i>
                <span class="links_name">Conta-Corrente</span>
            </a>
            <span class="tooltip">Conta-Corrente</span>
        </li>
        <li>
            <a href="#">
                <i class='bx bx-bar-chart-alt-2' ></i>
                <span class="links_name">Lançamentos</span>
            </a>
            <span class="tooltip">Lançamentos</span>
        </li>
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
    </ul>
</div>