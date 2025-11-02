<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adicionarLei.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><form action="home" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudHome.svg" alt="home">Home</button></form></li>
        <li><form action="menuAdm" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
        <li><form action="menuAss" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
        <li><form action="menuProdutor" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudProd.svg" alt="Produtor">Produtor</button></form></li>
        <li><form action="menuAtividade" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAtiv.svg" alt="Atividade">Atividade</button></form></li>
        <li><form action="menuAula" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAula.svg" alt="Assinatura">Aula</button></form></li>
        <li><a href="https://app.powerbi.com/view?r=eyJrIjoiOTdmYWNmYjktNWVlYi00ZjJlLWIyMWUtOWVmZGVhMzBjNGExIiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9"><img src="${pageContext.request.contextPath}/assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="${pageContext.request.contextPath}/assets/exit.svg" alt="exit">Sair</button></form></li>
    </ul>
</nav>
<%
    int idAula = (int) request.getAttribute("idAula");
%>
<form action="adicionarLeiCompleto" method="get" id="adicionar" autocomplete="off">
    <h1>Lei</h1>
    <label for="frente">Lei</label>
    <textarea
            name="lei"
            id="frente"
            rows="3"
            cols="25"
            placeholder="Digite a Lei"
    ></textarea>
    <label for="aula">Id aula</label>
    <input type="text" name="idAula" id="aula" placeholder="Digite o id da aula" autocomplete="off" class="add" value="<%=idAula%>">
    <button type="submit">Adicionar</button>
</form>
</body>
<style>
    label{
        font-size: 12px;
        margin-top: 30px;
        margin-bottom: -10px;
    }

    .add{
        font-size: 3px;
    }

</style>
</html>