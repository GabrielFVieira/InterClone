<%@page import="java.util.Map"%>
<table class="table table-sm table-hover table-bordered default-table mt-2" style="background-color: white">
    <thead>
        <th class="col-sm-5">Categoria</th>
        <th class="col-sm-4">Valor total</th>
        <th class="col-sm-3">%</th>
    </thead>
    <tbody>
        <%  Map<String, Double> dados = (Map<String, Double>) request.getAttribute("dados");
        
            Double total = 0.0;
            for(Double valor : dados.values()) {
                total += valor;
            }
        
            for(String categoria : dados.keySet()) {
                Double valor = dados.get(categoria);
        %>
        <tr>
            <td><%= categoria %></td>
            <td><%= String.format("R$ %1$,.2f",valor) %></td>
            <td><%= String.format("%1$,.2f", ((valor * 100) / total)) %>%</td>                            
        </tr>
        <% } %>
    </tbody>
</table>		