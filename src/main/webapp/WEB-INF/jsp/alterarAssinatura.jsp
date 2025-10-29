<%@ page import="com.zeta_servlet.model.Assinatura" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/alterarAssinatura.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><form action="home" method="post"><button type="submit"><img src="assets/crudHome.svg" alt="home">Home</button></form></li>
        <li><form action="menuAdm" method="post"><button type="submit"><img src="assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
        <li><form action="menuAss" method="post"><button type="submit"><img src="assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
        <li><a href="${pageContext.request.contextPath}/ATIVIDADE/menuAtividade.html"><img src="assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAula.html"><img src="assets/crudAula.svg" alt="Aula">Aula</a></li>
        <li><a href="../.."><img src="assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="assets/exit.svg" alt="exit">Sair</button></form></li>
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
    <label for="">Tipo plano</label>
    <input type="text" name="tpPlano" id="tpPlano" placeholder="Tipo do Plano" value="<%= tpPlano%>">
    <label for="">Quantidade Cursos</label>
    <input type="number" name="qtdCurso" id="tpPlano" placeholder="Quantidade de Cursos" value="<%= benefQtdCursos%>">
    <label for="">Descrição</label>
    <input type="text" name="descricao" id="descricao" placeholder="Descrição" value="<%= benefDescPlno%>">
    <label for="">Preço Fixo</label>
    <input type="number" name="precoFixo" id="precoFixo" placeholder="Preço Fixo" value="<%= precoFixo%>">
    <label for="">Preço por Produtor</label>
    <input type="number" name="precoProdutor" id="precoProdutor" placeholder="Preço por Produtor" value="<%= precoQtdProdutores%>">
    <input type="hidden" name="id" value="<%= id%>">
    <button type="submit">Alterar</button>
</form>
</body>
</html>
