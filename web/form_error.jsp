<%@page import="java.util.Map"%>
<%@page import="aplicacao.Validador"%>
<% Map<String, String> errosForm = request.getAttribute(Validador.ERROS) != null ? (Map<String, String>)request.getAttribute(Validador.ERROS) : null; %>
<% if(errosForm != null) { %>

    var errors = {
        <%  for (Map.Entry<String, String> entry : errosForm.entrySet()) { %>
                <%=entry.getKey()%>: "<%=entry.getValue()%>",
        <% } %>
    }
    validator.showErrors(errors);
<% } %>