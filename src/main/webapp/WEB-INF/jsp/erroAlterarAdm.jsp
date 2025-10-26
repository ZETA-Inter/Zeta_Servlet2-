<%@ page import="com.zeta_servlet.model.Adm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="ADMINISTRADOR/alterarAdministrador.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><a href="home"><img src="assets/crudHome.svg" alt="Home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/ASSINATURA/menuAssinatura.html"><img src="assets/crudAss.svg" alt="Assinatura">Assinatura</a></li>
        <li><a href="${pageContext.request.contextPath}/PRODUTOR/menuProdutor.html"><img src="assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/ATIVIDADE/menuAtividade.html"><img src="assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAula.html"><img src="assets/crudAula.svg" alt="Aula">Aula</a></li>
        <li><a href="../.."><img src="assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><a href="#"><img src="assets/exit.svg" alt="exit">Sair</a></li>
    </ul>
</nav>
<%
    Adm adm = (Adm) request.getAttribute("adm");
    String email = adm.getEmail();
    String senha = adm.getSenha();
    int id = adm.getId();

%>
<form action="alterarAdmCompleto" method="post">
    <h1>Administrador</h1>
    <label for="">Email</label>
    <input type="email" name="email" id="email" placeholder="Digite o novo email" value="<%= email%>" style="border-color: red">
    <p style="color: red">erro, senha ou email invalidos</p>
    <label for="">Senha</label>
    <input type="password" name="senha" id="senha" placeholder="Digite a nova senha" value="<%= senha%>" style="border-color: red">
    <input type="hidden" name="id" value="<%= id%>">
    <button type="submit">Alterar</button>
</form>
</body>
<style>
    body{
        padding: 0;
        top: 0;
        margin: 0;
        overflow: hidden;
        display: flex;
        align-items: center;
    }

    .nav-bar {
        width:300px;
        background-color: #043253;
        border-radius: 0px 25px 25px 0px;
        height: 740px;
        box-shadow: rgb(128, 128, 128) 1px 5px 30px 5px;
    }

    .nav-bar ul{
        width:100%;
        height:auto;
        margin:0;
        padding:0;
    }

    .nav-bar ul img {
        width: 40px;
        height: 40px;
    }

    .nav-bar li{
        list-style: none;
        width:86.7%;
    }

    .nav-bar li a {
        display: flex;
        align-items: center;
        gap: 20px;
        text-decoration:none;
        color:#ffffff;
        background-color:#043253;
        width: 100%;
        font-family:'Poppins';
        font-size: 18px;
        padding-left: 40px;
        padding-top: 10px;
        padding-bottom: 10px;
        text-align: left;
    }

    .nav-bar li a:hover{
        background-color:#18A4E1;
    }
    .nav-bar li a{
        transition: all .5s ease;
    }

    #logoMenu{
        width: 200px;
        margin-left: 40px;
    }
    form{
        box-shadow: 0px 5px 8px 0px rgba(0, 0, 0, 0.3);
        border-radius: 50px;
        width: 620px;
        height: 560px;
        margin: 0 auto;
        gap: 2px;
        padding: 20px;
        display: grid;
        font-family: 'Poppins';
        color: #043253;
    }
    form > h1{
        margin-top: 30px;
        text-align: center;
        font-size: 50px;
    }
    form > input{
        padding: 15px;
        height: 35px;
        border-radius: 20px;
        border-style: none;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        font-family: 'Poppins';
        font-size: 17.5px;
        color: #043253;
        width: 540px;
        margin: auto;
        margin-top: -65px;
    }

    form > label{
        font-size: 25px;
        margin-top: -40px;
        /* padding: 30px; */
        margin-left: 35px;
    }

    form > button{
        border-radius: 18px;
        background-color: #043253;
        border-style: none;
        color: #ffffff;
        width: 200px;
        font-size: 22.5px;
        height: 55px;
        font-family: 'Poppins';
        margin: 0 auto;
        transition: all .5s ease;
    }

    form > button:hover{
        background-color: #18A4E1;
    }
</style>
</html>
