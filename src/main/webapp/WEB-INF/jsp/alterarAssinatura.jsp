<%@ page import="com.zeta_servlet.model.Assinatura" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alterarAssinatura.css">
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
    Assinatura ass = (Assinatura) request.getAttribute("ass");
    int id = ass.getId();
    String tpPlano = ass.getTpPlano();
    int benefQtdCursos = ass.getBenefQtdCursos();
    String benefDescPlno = ass.getBenefDescPlno();
    BigDecimal precoFixo = ass.getPrecoFixo();
    BigDecimal precoQtdProdutores = ass.getPrecoQtdProdutores();
%>
<form action="alterarAssCompleto" method="post" id="alterar">
    <h1>Assinatura</h1>
    <label for="tpPlano">Tipo plano</label>
    <input type="text" name="tpPlano" id="tpPlano" placeholder="Tipo do Plano" value="<%= tpPlano%>">
    <label for="tpPlano">Quantidade Cursos</label>
    <input type="number" name="qtdCurso" id="tpPlano" placeholder="Quantidade de Cursos" value="<%= benefQtdCursos%>">
    <label for="descricao">Descrição</label>
    <input type="text" name="descricao" id="descricao" placeholder="Descrição" value="<%= benefDescPlno%>">
    <label for="precoFixo">Preço Fixo</label>
    <input type="number" name="precoFixo" id="precoFixo" placeholder="Preço Fixo" value="<%= precoFixo%>">
    <label for="precoProdutor">Preço por Produtor</label>
    <input type="number" name="precoProdutor" id="precoProdutor" placeholder="Preço por Produtor" value="<%= precoQtdProdutores%>">
    <input type="hidden" name="id" value="<%= id%>">
    <button type="submit">Alterar</button>
</form>
</body>
</html>
