<%@ page import="java.util.List" %>
<%@ page import="com.zeta_servlet.model.Adm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/home.html"><img src="assets/crudHome.svg" alt="Home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAssinatura.html"><img src="assets/crudAss.svg" alt="Assinatura">Assinatura</a></li>
        <li><a href="${pageContext.request.contextPath}/menuProdutor.html"><img src="assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAtividade.html"><img src="assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAula.html"><img src="assets/crudAula.svg" alt="Aula">Aula</a></li>
        <li><a href="../.."><img src="assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><a href="#"><img src="assets/exit.svg" alt="exit">Sair</a></li>
    </ul>
</nav>
<div id="consultar">
    <h1>Administrador</h1>
    <form action="">
        <label for=""><img src="assets/lupa.svg"></label>
        <input type="text" name="buscar" id="bsucar" placeholder="Buscar" >
    </form>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>Email</td>
            <td>Senha</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <%
            List<Adm> lisA = (List<Adm>) request.getAttribute("list");
            System.out.println(lisA);
            for (int i = 0; i < lisA.size(); i++) {
            String email = lisA.get(i).getEmail();
            String senha = lisA.get(i).getSenha();
            int id = lisA.get(i).getId();

        %>
        <tr>
            <td><%= id%></td>
            <td><%= email%></td>
            <td><%= senha%></td>
            <form action="alterarAdm">
                <input type="hidden" value="<%= i%>" name="i">
            <td><button type="submit"><img src="assets/alterar.svg"></button>
            </form>

            <form action="deletarAdm" method="post">
                <input type="hidden" value="<%= id%>" name="id">
                <button type="submit"><img src="assets/deletar.svg"></button>
            </form>
            </td>
        </tr>
        <%
            }%>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/adicionarAdministrador.html">
        <div id="adicionar">
            <p>+ Adicionar Adm</p>
        </div>
    </a>
</div>
<style>
    body{
        padding: 0;
        top: 0;
        margin: 0;
        overflow: hidden;
        display: flex;
        font-family: 'Poppins';
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
        width: 38px;
        height: 38px;
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
    #consultar{
        margin-left: 100px;
        margin-top: 90px;
    }
    #consultar > h1{
        font-family: 'Poppins';
    }
    form{
        box-shadow: 0px 5px 8px 0px rgba(176, 175, 175, 0.967);
        border-radius: 20px;
        border-color: black;
        width: 900px;
        height: 40px;
        padding: 7px;
        display: flex;
        align-items: center;
        gap: 15px;
    }
    form > input{
        border: none;
        outline: none;
        font-size: larger;
    }
    form > label > img{
        width: 64%;
        margin-left: 10px;
    }
    table{
        margin-top: 40px;
        border-collapse: separate;
        border-spacing: 0px 1px;
        overflow: hidden;
        width: 100%;
        border-radius: 10px;
        margin-bottom: 40px;
        background-color: rgba(252, 247, 241, 1);
    }
    thead{
        background-color:#043253;
        color: aliceblue;
        gap: 20px;
    }
    td,th{
        padding: 20px;
        text-align: center;
    }
    td > a{
        margin: 5px;
    }
    tr:nth-child(even){
        background-color: rgba(173, 173, 173, 1);
    }
    #adicionar{
        border: solid 1px rgba(24, 164, 225, 1);
        border-radius: 13px;
        padding: 5px;
        height: 35px;
        align-items: center;
        display: flex;
        width: 160px;
        color: rgba(24, 164, 225, 1);
    }
    #adicionar > p{
        margin: 0 auto
    }
    a,
    a:visited,
    a:hover,
    a:active {
        text-decoration: none;
        color: inherit;
    }
</style>
</body>
</html>
