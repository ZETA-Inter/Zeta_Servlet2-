<%@ page import="com.zeta_servlet.model.Aula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alterarAula.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><form action="home" method="post" id="fHome"><button type="submit"><img src="../assets/crudHome.svg" alt="home">Home</button></form></li>
        <li><form action="menuAdm" method="post" id="fAdm"><button type="submit"><img src="../assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
        <li><form action="menuAss" method="post"><button type="submit"><img src="../assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
        <li><a href="${pageContext.request.contextPath}/menuProdutor.html"><img src="../assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAtividade.html"><img src="../assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><form action="../menuAula" method="post"><button type="submit"><img src="../assets/crudAula.svg" alt="Aula">Aula</button></form></li>
        <li><a href="../.."><img src="../assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="../assets/exit.svg" alt="exit">Sair</button></form></li>
    </ul>
</nav>
<%
    Aula aula = (Aula) request.getAttribute("au");
    String nome = aula.getNome();
    int idModulo = aula.getIdModulo();
    int id = aula.getId();

%>

<form action="alterarAulaCompleto" method="post" id="alterar">
    <h1>Aula</h1>
    <label for="">Nome</label>
    <input type="text" name="nome" id="nome" value="<%= nome%>">
    <label for="">Id do Módulo</label>
    <input type="number" name="idModulo" id="idModulo" value="<%= idModulo%>">
    <input type="hidden" name="id" value="<%= id%>">
    <button type="submit">Alterar</button>
</form>
</body>
</html>
