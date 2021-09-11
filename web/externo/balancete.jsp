<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>InterClone | Balancete</title>
        <%@include file="/header.html" %>
    </head>
    <body>
        <%@page import="aplicacao.Conta"%>
        <%@page import="java.util.List"%>
        <%@page import="java.util.ArrayList"%>
        <%@page import="java.util.Map"%>
        
        <jsp:include page="/externo/sidebar.jsp" />
        <section class="home-section">
            <div class="main-form">
                <h2 class="text-center mb-3">Balancete</h2>
                
                <a onClick="gerarURL('lancamentos')" id="btnCancel">
                    <img src="images/icons/close.png" alt="Voltar" title="Voltar" />
                </a>
                
                <div class="d-flex justify-content-between mb-2">
                    <a class="table-button btn mr-2 text-white" onClick="gerarURL('#')" style="margin-right: auto;">Alterar Visualização</a>
                    <div class="d-flex flex-row align-items-center" style="max-width: 250px; margin-left: auto;">
                        <label class="mb-0 mr-2" for="account">Conta: </label>
                        <select id="account" name="account" class="form-control text-truncate" onchange="onContaChange()">                           
                            <%
                            if(request.getAttribute("contas") != null) {
                                List<Conta> listaContas = (List<Conta>) request.getAttribute("contas");
                                String contaSelecionada = request.getParameter("conta"); %>
                                <option <%= contaSelecionada == null || "0".equals(contaSelecionada) ? "selected" : "" %> value="0">Todas</option>
                                <%
                                for (int i = 0; i < listaContas.size(); i++) {
                                    Conta conta = listaContas.get(i);
                            %>
                            <option <%= contaSelecionada != null && contaSelecionada.equals(conta.getId().toString()) ? "selected" : "" %> 
                                value="<%=conta.getId().toString() %>"><%=conta.getNome()%></option>
                            <%
                                }
                            }
                            %>
                        </select>
                    </div>
                </div>
                <div class="chart-container mt-4">
                    <canvas id="myChart" />
                </div>
            </div>
        </section>
        <%@include file="/base_scripts.html" %>
        <script src="js/chart.min.js"></script>
        <script src="js/chartjs-plugin-datalabels.min.js"></script>
        
        <%  Map<String, Double> dados = (Map<String, Double>) request.getAttribute("dados");
            List<String> categorias = new ArrayList<>();
            List<Double> valores = new ArrayList<>();
            
            for(String c : dados.keySet()) {
                categorias.add(c);
                valores.add(dados.get(c));
            }
        %>
        
        <script>                
            const dynamicColors = function() {
                var r = Math.floor(Math.random() * 255);
                var g = Math.floor(Math.random() * 255);
                var b = Math.floor(Math.random() * 255);
                return "rgb(" + r + "," + g + "," + b + ")";
            };

            const data = {
                labels: [
                  <% for(String categoria : categorias) { %>
                      "<%= categoria %>",
                  <% } %>
                ],
                datasets: [{
                  data: <%= valores.toString() %>,
                  backgroundColor: [
                  <% for(Double valor : valores) { %>
                      dynamicColors(),
                  <% } %>
                  ],
                  hoverOffset: 4
                }]
              };

            const ctx = document.getElementById('myChart');
            const myChart = new Chart(ctx, {
                type: 'pie',
                data: data,
                plugins: [ChartDataLabels],
                options: {
                  responsive: true,
                  maintainAspectRatio: true,
                  plugins: {
                    legend: {
                      position: 'top',
                    },
                    title: {
                      display: false,
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                var label = context.label || '';

                                if (label) {
                                    label += ': R$';
                                }
                                if (context.parsed.y !== null) {
                                    label += context.parsed.toLocaleString('pt-BR', {
                                        minimumFractionDigits: 2,
                                        useGrouping: false
                                    });
                                }

                                return label;
                            }
                        }
                    },
                    datalabels: {
                        formatter: (value, ctx) => {
                            let sum = 0;
                            let dataArr = ctx.chart.data.datasets[0].data;
                            dataArr.map(data => {
                                sum += data;
                            });

                            let percentage = (value*100 / sum).toFixed(2)+"%";
                            return percentage;
                        },
                        color: '#fff',
                        font: {
                            size: "20",
                            weight: "bold",
                        },
                    },
                  },
                },
            });
            
            function onContaChange() {
                const account = document.getElementById("account").value;
                
                if(account === '0') {
                    window.location.href = "balancete";
                } else {
                    window.location.href = "balancete?conta=" + account;   
                }
            }
        </script>
    </body>
</html>
