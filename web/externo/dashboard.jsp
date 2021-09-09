<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
            <title>InterClone | Dashboard</title>
            <%@include file="/header.html" %>
	</head>
	<body>
            <jsp:include page="/externo/sidebar.jsp" />
            <section class="home-section flex-column">
                <div class="text">
                    <h1>Dashboard do Usuário</h1>
                    Bem-vindo a Dashboard temporária do usuário. <br> 
                    Para essa entrega o único botão funcional na barra à esquerda é o <b>Sair</b> <br><br> 
                    Caso deseje acessar a página temporária com os links para os formulários de cadastro de Conta-Corrente e Lançamentos <a href="externo">clique aqui</a>
                </div>
                
                <div class="chart-container">
                    <canvas id="myChart" />
                </div>
            </section>

            <%@include file="/base_scripts.html" %>
            <script src="js/chart.min.js"></script>
            <script>
                var ctx = document.getElementById('myChart');
                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                        datasets: [{
                            label: 'Teste',
                            data: [8, 4, 9],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)'
                            ],
                            borderWidth: 1
                        },
                        {
                            hidden: true,
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        tension: 0.2,
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
                </script>
	</body>
</html>
