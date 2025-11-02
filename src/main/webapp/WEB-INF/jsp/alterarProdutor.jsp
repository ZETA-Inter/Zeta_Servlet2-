<%@ page import="com.zeta_servlet.model.Produtor" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/alterarProdutor.css">
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
    Produtor produtor = (Produtor) request.getAttribute("produ");
    int id = produtor.getId();
    String cpf = produtor.getCpf();
    LocalDate dtNascimento = produtor.getDt_nascimento();
    String email = produtor.getEmail();
    String senha = produtor.getSenha();
    int pontos = produtor.getPontos_acumulados();
    String nome = produtor.getNome_primeiro();
    String sobrenome = produtor.getNome_ultimo();
    int aulasFeitas = produtor.getAulas_feitas();
    int idFornecedor = produtor.getId_fornecedor();
    int idAssinatura = produtor.getId_assinatura();

%>

<form action="AlterarProdutor" method="post">
    <h1>Produtor</h1>
    <label for="email">Email</label>
    <input type="email" name="email" id="email" value="<%= email%>">
    <label for="senha">Senha</label>
    <input type="password" name="senha" id="senha" value="<%= senha%>">
    <label for="pontos">Pontos</label>
    <input type="number" name="pontos" id="pontos" value="<%= pontos%>">

    <div class="campos">
        <label for="nome">Nome<input type="text" name="nome" id="nome" value="<%= nome%>"></label>

        <label for="sobrenome">Sobrenome <input type="text" name="sobrenome" id="sobrenome" value="<%= sobrenome%>"></label>
    </div>

    <div class="campos2">
        <label for="aulas">Aulas<input type="number" name="aulas" id="aulas" value="<%= aulasFeitas%>"></label>

        <label for="idFornecedor">Id fornecedor <input type="number" name="idFornecedor" id="idFornecedor" value="<%= idFornecedor%>"></label>
        <label for="idAssinatura">Id Assinatura <input type="number" name="idAssinatura" id="idAssinatura" value="<%= idAssinatura%>"></label>
    </div>

    <input type="hidden" name="id" value="<%= id%>">
    <input type="hidden" name="dtNascimento" value="<%= dtNascimento%>">
    <input type="hidden" name="cpf" value="<%= cpf%>">

    <button type="submit">Alterar</button>
</form>
</body>
</html>