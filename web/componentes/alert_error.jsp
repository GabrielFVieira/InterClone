<%@page import="java.util.Map"%>
<%@page import="aplicacao.Validador"%>
<% Map<String, String> erros = request.getAttribute(Validador.ERROS) != null ? (Map<String, String>)request.getAttribute(Validador.ERROS) : null; %>
<% if(erros != null && erros.containsKey(Validador.ALERTA)) { %>
    <div class="alert alert-danger text-center" role="alert">
        <%=erros.get(Validador.ALERTA)%>
    </div>
<% } %>