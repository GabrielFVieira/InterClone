<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <div class="logo-details">
        <i class="bx bxl-linkedin icon"></i>
        <div class="logo_name">InterClone</div>
        <i class="bx bx-menu" id="btn"></i>
    </div>
    <ul class="nav-list">
        <li>
            <a onClick="gerarURL('dashboard')">
                <i class="bx bx-grid-alt"></i>
                <span class="links_name">Dashboard</span>
            </a>
            <span class="tooltip">Dashboard</span>
        </li>
        <li>
            <a onClick="gerarURL('#')">
                <i class="bx bx-money"></i>
                <span class="links_name">Conta-Corrente</span>
            </a>
            <span class="tooltip">Conta-Corrente</span>
        </li>
        <li>
            <a onClick="gerarURL('lancamentos')">
                <i class='bx bx-bar-chart-alt-2' ></i>
                <span class="links_name">Lançamentos</span>
            </a>
            <span class="tooltip">Lançamentos</span>
        </li>
        <jsp:include page="/componentes/sidebar_profile.jsp" />
    </ul>
</div>