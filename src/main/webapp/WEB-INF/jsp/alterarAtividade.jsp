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
    <title>Alterar Atividade</title>
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
    if (atividade == null) {
        response.sendRedirect(request.getContextPath() + "/menuAtividade");
        return;
    }

    int id = atividade.getId();
    double pontuacao = atividade.getPontuacao();
    List<Pergunta> perguntas = atividade.getPerguntas();
    List<Alternativa> alternativas = atividade.getAlternativas();
    int id_aula = atividade.getId_aula();

    // Assumindo que há apenas uma pergunta por atividade
    Pergunta pergunta = perguntas != null && !perguntas.isEmpty() ? perguntas.get(0) : null;
%>

<form action="${pageContext.request.contextPath}/AlterarAtividade" method="post">
    <h1>Alterar Atividade</h1>

    <div class="form-group">
        <label for="pontos">Pontuação da Atividade</label>
        <input type="number" name="pontos" id="pontos" value="<%= pontuacao %>" step="0.1" required>
    </div>

    <div class="form-group">
        <label for="pergunta">Pergunta</label>
        <textarea name="pergunta" id="pergunta" rows="3" required><%= pergunta != null && pergunta.getPergunta() != null ? pergunta.getPergunta() : "" %></textarea>
    </div>

    <div class="form-group">
        <label for="alternativaSelecionada">Selecionar Alternativa para Alterar</label>
        <select name="alternativaSelecionada" id="alternativaSelecionada" required>
            <option value="">Selecione uma alternativa</option>
            <%
                if (alternativas != null) {
                    for (int i = 0; i < alternativas.size(); i++) {
                        Alternativa alternativa = alternativas.get(i);
                        String textoAlternativa = alternativa.getAlternativa() != null ? alternativa.getAlternativa() : "";
            %>
            <option value="<%= i %>">Alternativa <%= i + 1 %>: <%= textoAlternativa.length() > 50 ? textoAlternativa.substring(0, 50) + "..." : textoAlternativa %></option>
            <%
                    }
                }
            %>
        </select>
    </div>

    <div class="form-group">
        <label for="novaAlternativa">Nova Alternativa</label>
        <input type="text" name="novaAlternativa" id="novaAlternativa" placeholder="Digite o novo texto da alternativa">
    </div>

    <div class="form-group">
        <label for="correta">Alternativa Correta?</label>
        <select name="correta" id="correta" required>
            <option value="false">Não</option>
            <option value="true">Sim</option>
        </select>
    </div>

    <div class="form-group">
        <label for="idAula">ID da Aula</label>
        <input type="number" name="idAula" id="idAula" value="<%= id_aula %>" readonly>
    </div>

    <input type="hidden" name="id" value="<%= id %>">

    <div class="form-buttons">
        <button type="submit">Alterar Atividade</button>
        <a href="${pageContext.request.contextPath}/menuAtividade" class="cancel-btn">Cancelar</a>
    </div>
</form>

<script>
    // Preencher automaticamente o campo de nova alternativa quando selecionar uma alternativa
    document.getElementById('alternativaSelecionada').addEventListener('change', function() {
        var selectedIndex = this.value;
        var alternativas = [
            <%
                if (alternativas != null) {
                    for (int i = 0; i < alternativas.size(); i++) {
                        Alternativa alternativa = alternativas.get(i);
                        String texto = alternativa.getAlternativa() != null ? alternativa.getAlternativa().replace("\"", "\\\"") : "";
            %>
            "<%= texto %>",
            <%
                    }
                }
            %>
        ];

        if (selectedIndex !== "" && alternativas[selectedIndex]) {
            document.getElementById('novaAlternativa').value = alternativas[selectedIndex];
        } else {
            document.getElementById('novaAlternativa').value = '';
        }

        // Preencher também o select de alternativa correta
        var alternativaCorretaIndex = -1;
        <%
            if (alternativas != null) {
                for (int i = 0; i < alternativas.size(); i++) {
                    Alternativa alternativa = alternativas.get(i);
                    if (alternativa.isCorreto()) {
        %>
        alternativaCorretaIndex = <%= i %>;
        <%
                    }
                }
            }
        %>

        if (selectedIndex == alternativaCorretaIndex) {
            document.getElementById('correta').value = "true";
        } else {
            document.getElementById('correta').value = "false";
        }
    });
</script>
</body>
</html>