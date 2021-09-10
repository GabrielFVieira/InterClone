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
            <a href="usuarios">
                <i class="bx bx-user"></i>
                <span class="links_name">Usuários</span>
            </a>
            <span class="tooltip">Usuários</span>
        </li>
        <li>
            <a href="administradores">
                <i class="bx bx-task-x"></i>
                <span class="links_name">Administradores</span>
            </a>
            <span class="tooltip">Administradores</span>
        </li>
        <li>
            <a href="categorias">
                <i class="bx bxs-category"></i>
                <span class="links_name">Categorias</span>
            </a>
            <span class="tooltip">Categorias</span>
        </li>
        <jsp:include page="/componentes/sidebar_profile.jsp" />
    </ul>
</div>