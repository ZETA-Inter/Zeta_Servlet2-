
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZETA - Bem-estar Animal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO ZETA - 5.png" type="image/x-icon">
    <link
            href="https://fonts.googleapis.com/css2?family=Spartan:wght@300;400;500;600;700&family=Poppins:wght@300;400;500&display=swap"
            rel="stylesheet">
</head>

<body>
<header class="site-header">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/assets/Group 12229.svg" alt="Logo ZETA">
        <div class="brand" aria-label="ZETA">ZETA</div>
    </div>
    <nav class="nav" aria-label="Menu principal">
        <ul class="nav-list">
            <li><a href="index.html#home" class="is-active">Início</a></li>
            <li><a href="index.html#funcionalidades" class="is-active">Funcionalidades</a></li>
            <li><a href="index.html#planos" class="is-active">Planos</a></li>
            <li><a href="index.html#contato" class="is-active">Contato</a></li>
            <li><a href="admin.html" class="is-active">Admin</a></li>
        </ul>
    </nav>
</header>

<main class="main">
    <section class="login">
        <div class="admContent">
            <h1 id="Adm">ADMIN</h1>
            <div class="buttons">
                <button id="Login">Login</button>
            </div>

            <form class="loginAdmin" action="loginAdmin" method="post">
                <div id="emailLogin" style="border: 2px solid red">
                    <label for="emailUser"><img id="email" src="${pageContext.request.contextPath}/assets/emailAdmin.svg" alt="Email"></label>
                    <input type="email" name="email" id="emailUser" placeholder= "Digite seu e-mail" required>
                </div>
                <p style="color: red; font-size: 15px; margin: 0 auto; margin-top: -25px; margin-left: 10px;">Erro ao realizar o login, verifique as informações</p>
                <div id="senhaAdmin" style="border: 2px solid red">
                    <label for="senhaUser"><img id="senha" src="${pageContext.request.contextPath}/assets/senhaAdmin.svg" alt="senha"></label>
                    <input type="password" name="userpassword" id="senhaUser" placeholder="Digite sua senha" required >
                </div>

                <div class="entraesq">
                    <p id="esqsenha">Esqueceu sua senha?</p>
                    <button id="Entrar" type="submit">Entrar</button>
                </div>
            </form>




            <div class="Imagens">
                <img class="elipseAz" src="${pageContext.request.contextPath}/assets/elipseazulqseescuro.png" alt="ElementoAzul">

                <img class="elipseAzul" src="${pageContext.request.contextPath}/assets/elipseazulclaro.png" alt="ElementoAzulClaro">

                <img class="elipseBranca" src="${pageContext.request.contextPath}/assets/elipseBranca.png" alt="ElementoBranco">

                <img class="boiAdmin" src="${pageContext.request.contextPath}/assets/boiAdmin.png" alt="LogoZeta">
            </div>

        </div>

    </section>

</main>
</body>
</html>
