<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
                labels: {
                    font: {
                        size: 18,
                    }
                },
            },
            title: {
              display: false,
            },
            tooltip: {
                callbacks: {
                    label: function(context) {
                        var label = context.label || '';

                        if (label) {
                            label += ': R$ ';
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
</script>