<%@ page import="com.zeta_servlet.model.TextoCorrido" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alterarFlash.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><form action="${pageContext.request.contextPath}/home" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudHome.svg" alt="home">Home</button></form></li>
        <li><form action="${pageContext.request.contextPath}/menuAdm" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
        <li><form action="${pageContext.request.contextPath}/menuAss" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
        <li><a href="${pageContext.request.contextPath}/menuProdutor.html"><img src="${pageContext.request.contextPath}/assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAtividade.html"><img src="${pageContext.request.contextPath}/assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><form action="menuAula" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAula.svg" alt="Assinatura">Aula</button></form></li>
        <li><a href="https://app.powerbi.com/view?r=eyJrIjoiOTdmYWNmYjktNWVlYi00ZjJlLWIyMWUtOWVmZGVhMzBjNGExIiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9"><img src="${pageContext.request.contextPath}/assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="${pageContext.request.contextPath}/assets/exit.svg" alt="exit">Sair</button></form></li>
    </ul>
</nav>
<%
    TextoCorrido textoCorrido = (TextoCorrido) request.getAttribute("texto");
    int idAula  = textoCorrido.getIdAula();
    String texto = textoCorrido.getTextoCorrido();
    int id = textoCorrido.getId();

%>
<form action="alterarTextoCompleto" method="get" id="adicionar" autocomplete="off">
    <h1>Lei</h1>
    <label for="frente">Texto Corrido</label>
    <textarea
            name="texto"
            id="frente"
            rows="3"
            cols="25"
            placeholder="Digite o Texto Corrido"
    ><%=texto%></textarea>
    <label for="">Id aula</label>
    <input type="text" name="idAula" id="aula" placeholder="Digite o id da aula" autocomplete="off" class="add" value="<%=idAula%>">
    <input type="hidden" value="<%= id%>" name="id">
    <button type="submit">Alterar</button>
</form>
</body>
<style>
    label{
        font-size: 12px;
        padding: 0;
        border: 0;
        margin-top: -100px;
        margin-bottom: -250px;
        height: 15px;
    }

    .add{
        font-size: 3px;
    }

    textarea{
        margin: 0;
    }
</style>
</html>