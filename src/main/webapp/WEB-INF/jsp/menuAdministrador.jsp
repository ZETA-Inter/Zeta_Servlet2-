<%@ page import="java.util.List" %>
<%@ page import="com.zeta_servlet.model.Adm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuAdministrador.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
    <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
    <img src="assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
    <ul>
        <li><form action="home" method="post" id="fHome"><button type="submit"><img src="assets/crudHome.svg" alt="home">Home</button></form></li>
        <li><form action="menuAdm" method="post" id="fAdm"><button type="submit"><img src="assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
        <li><a href="${pageContext.request.contextPath}/menuAssinatura.html"><img src="assets/crudAss.svg" alt="Assinatura">Assinatura</a></li>
        <li><a href="${pageContext.request.contextPath}/menuProdutor.html"><img src="assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAtividade.html"><img src="assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAula.html"><img src="assets/crudAula.svg" alt="Aula">Aula</a></li>
        <li><a href="../.."><img src="assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="assets/exit.svg" alt="exit">Sair</button></form></li>

    </ul>
</nav>
<div id="consultar">
    <h1>Administrador</h1>
    <form action="" id="buscar">
        <label for=""><img src="assets/lupa.svg"></label>
        <input type="text" name="buscar" id="bsucar" placeholder="Buscar" >
    </form>
    <table id="title" style="padding: 0; margin: 0; border-radius: 12px">
        <thead style="border-radius: 12px">
        <tr style="border-radius: 12px">
            <td class="name-title" style="text-align: left">ID</td>
            <td class="name-title" style="text-align: center">Email</td>
            <td class="name-title"></td>
            <td class="name-title"></td>
            <td class="name-title" style="text-align: left;">Senha</td>
        </tr>
        </thead>
    </table>
    <div id="tabela">
        <table style="padding: 0; margin: 0;">
            <tbody style="border-radius: 12px">
            <%
                List<Adm> lisA = (List<Adm>) request.getAttribute("list");
                System.out.println(lisA);
                for (int i = 0; i < lisA.size(); i++) {
                    String email = lisA.get(i).getEmail();
                    String senha = lisA.get(i).getSenha();
                    int id = lisA.get(i).getId();

            %>
            <tr style="padding: 0; border-radius: 12px">
                <td style="padding: 0;"><%= id%></td>
                <td><%= email%></td>
                <td><%= senha%></td>
                <td><form action="alterarAdm" id="alterar">
                    <input type="hidden" value="<%= i%>" name="i">
                    <button type="submit"><img src="assets/alterar.svg"></button>
                </form>

                    <form action="deletarAdm" method="post" id="deletar">
                        <input type="hidden" value="<%= id%>" name="id">
                        <button type="submit"><img src="assets/deletar.svg"></button>
                    </form></td>

            </tr>
            <%
                }%>
            </tbody>
        </table>
    </div>

    <a href="${pageContext.request.contextPath}/html/adicionarAdministrador.html">
        <div id="adicionar" style="margin-top: 20px">
            <p>+ Adicionar Adm</p>
        </div>
    </a>
</div>
</body>
</html>
