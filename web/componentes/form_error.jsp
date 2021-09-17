<%@page import="java.util.Map"%>
<%@page import="aplicacao.Validador"%>
<% Map<String, String> errosForm = request.getAttribute(Validador.ERROS) != null ? (Map<String, String>)request.getAttribute(Validador.ERROS) : null; %>
<% if(errosForm != null && !(errosForm.size() == 1 && errosForm.containsKey(Validador.ALERTA))) { %>

    var errors = {
        <%  for (Map.Entry<String, String> entry : errosForm.entrySet()) { 
                if (!Validador.ALERTA.equals(entry.getKey())) { %>
                    <%=entry.getKey()%>: "<%=entry.getValue()%>",
        <%      }
            } %>
    }
    validator.showErrors(errors);
<% } %>