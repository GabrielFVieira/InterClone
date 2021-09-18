<%@page import="java.util.Map"%>
<%@page import="aplicacao.Validador"%>
<% Map<String, String> erros = request.getAttribute(Validador.ERROS) != null ? (Map<String, String>)request.getAttribute(Validador.ERROS) : null; %>
<% if(erros != null && erros.containsKey(Validador.ALERTA)) { %>
    <div class="alert alert-danger text-center alert-dismissible fade show" role="alert">
        <%=erros.get(Validador.ALERTA).replace("\n", "<br>") %>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
<% } %>