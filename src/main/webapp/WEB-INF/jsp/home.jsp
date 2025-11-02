
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String admin = (String) session.getAttribute("role");
    if (admin == null) {
        response.sendRedirect("/WEB-INF/errorPage/erro500.jsp");
        return;
    }
%>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="assets/LOGO ZETA - 5.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="assets/LOGO ZETA - 5.png" alt="Logo" id="logoMenu">
    <ul>
        <li><form action="home" method="post"><button type="submit"><img src="assets/crudHome.svg" alt="home">Home</button></form></li>
        <li><form action="menuAdm" method="post"><button type="submit"><img src="assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
        <li><form action="menuAss" method="post"><button type="submit"><img src="assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
        <li><a href="/PRODUTOR/menuProdutor.html"><img src="assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="/ATIVIDADE/menuAtividade.html"><img src="assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><form action="menuAula" method="post"><button type="submit"><img src="assets/crudAula.svg" alt="Assinatura">Aula</button></form></li>
        <li><a href="https://app.powerbi.com/view?r=eyJrIjoiOTdmYWNmYjktNWVlYi00ZjJlLWIyMWUtOWVmZGVhMzBjNGExIiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9"><img src="assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="assets/exit.svg" alt="exit">Sair</button></form></li>

    </ul>
</nav>

<div class="BemVindo">
    <div class="logoB">
        <h1 id="ZETA">ZETA</h1>
        <img src="assets/boiHome.png" alt="Logo ZETA">
    </div>
    <h2>Bem vindo ao CRUD</h2>
    <div class="element">
        <img src="assets/pata2.png" alt="Elemento pata traçada">
    </div>
</div>
</body>
</html>
