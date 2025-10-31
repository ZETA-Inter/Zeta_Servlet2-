<%@ page import="com.zeta_servlet.model.Atividade" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zeta_servlet.model.Pergunta" %>
<%@ page import="com.zeta_servlet.model.Alternativa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alterarAtividade.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/home.jsp"><img src="${pageContext.request.contextPath}/assets/crudHome.svg" alt="Home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/menuAdministrador.jsp"><img src="${pageContext.request.contextPath}/assets/crudAdm.svg" alt="Adm">Administrador</a></li>
        <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/menuAssinatura.jsp"><img src="${pageContext.request.contextPath}/assets/crudAss.svg" alt="Assinatura">Assinatura</a></li>
        <li><a href="${pageContext.request.contextPath}/html/menuProdutor.html"><img src="${pageContext.request.contextPath}/assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/menuAtividade.jsp"><img src="${pageContext.request.contextPath}/assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><a href="${pageContext.request.contextPath}/html/menuAula.html"><img src="${pageContext.request.contextPath}/assets/crudAula.svg" alt="Aula">Aula</a></li>
        <li><a href=".."><img src="${pageContext.request.contextPath}/assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><a href="#"><img src="${pageContext.request.contextPath}/assets/exit.svg" alt="exit">Sair</a></li>
    </ul>
</nav>

<%
    Atividade atividade = (Atividade) request.getAttribute("ativ");
    int i = (int) request.getAttribute("i");
    double pontuacao = atividade.getPontuacao();
    List<Pergunta> perguntas = atividade.getPerguntas();
    List<Alternativa> alternativas = atividade.getAlternativas();
    boolean correta = atividade.getAlternativas().get(i).isCorreto();
    int id_aula = atividade.getId_aula();

%>

<form action="${pageContext.request.contextPath}/AlterarAtividade" method="post">
    <h1>Atividade</h1>

    <label for="pontos">Pontuação</label>
    <input type="number" name="pontos" id="pontos" value="<%= pontuacao%>">

    <label for="pergunta">Pergunta</label>
    <input type="text" name="pergunta" id="pergunta" value="<%= perguntas%>">

    <label for="alternativa">Alternativa</label>
    <input type="text" name="alternativa" id="alternativa" value="<%= alternativas%>">

    <label for="correta">Correta</label>
    <input type="text" name="correta" id="correta" value="<%= correta%>">

    <label for="idAula">Id da aula</label>
    <input type="number" name="idAula" id="idAula" value="<%= id_aula%>">

    <input type="hidden" name="id" value="<%= i%>">

    <button type="submit">Alterar</button>
</form>
</body>
</html>
