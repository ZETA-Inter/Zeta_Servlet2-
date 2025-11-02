<%@ page import="java.util.List" %>
<%@ page import="com.zeta_servlet.model.Assinatura" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuAssinatura.css">
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
        <li><form action="menuAss" method="post"><button type="submit"><img src="assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
        <li><a href="${pageContext.request.contextPath}/menuProdutor.html"><img src="assets/crudProd.svg" alt="Produtor">Produtor</a></li>
        <li><a href="${pageContext.request.contextPath}/menuAtividade.html"><img src="assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
        <li><form action="menuAula" method="post"><button type="submit"><img src="assets/crudAula.svg" alt="Assinatura">Aula</button></form></li>
        <li><a href="https://app.powerbi.com/view?r=eyJrIjoiOTdmYWNmYjktNWVlYi00ZjJlLWIyMWUtOWVmZGVhMzBjNGExIiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9"><img src="assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
        <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="assets/exit.svg" alt="exit">Sair</button></form></li>

    </ul>
</nav>
<div id="consultar">
    <h1>Assinatura</h1>

    <div id="query">
        <div id="buscas">
            <label for="campoBusca"><img src="assets/lupa.svg"></label>
            <input type="text" name="index" id="campoBusca" placeholder="Buscar, ou inserir código de busca (ex: email:, id:)" >
        </div>
    </div>

    </table>
    <div id="tabela">
        <table style="padding: 0; margin: 0;">
            <thead style="border-radius: 12px; position: sticky; z-index: 20; top: 0;">
            <tr style="border-radius: 12px">
                <td class="name-title" style="text-align: center">ID</td>
                <td class="name-title" style="text-align: center">tpPlano</td>
                <td class="name-title" style="text-align: center;">benefQtdCursos</td>
                <td class="name-title" style="text-align: center;">benefDescPlno</td>
                <td class="name-title" style="text-align: center;">precoFixo</td>
                <td class="name-title" style="text-align: center;">precoQtdProdutores</td>
                <td class="name-title" style="text-align: center;"></td>
                <td class="name-title" style="text-align: center;"></td>
            </tr>
            </thead>
            <tbody style="border-radius: 12px">
            <%
                List<Assinatura> lisAs = (List<Assinatura>) request.getAttribute("list");
                for (int i = 0; i < lisAs.size(); i++) {
                    int id = lisAs.get(i).getId();
                    String tpPlano = lisAs.get(i).getTpPlano();
                    int benefQtdCursos = lisAs.get(i).getBenefQtdCursos();
                    String benefDescPlno = lisAs.get(i).getBenefDescPlno();
                    BigDecimal precoFixo = lisAs.get(i).getPrecoFixo();
                    BigDecimal precoQtdProdutores = lisAs.get(i).getPrecoQtdProdutores();

            %>
            <tr style="padding: 0; border-radius: 12px">
                <td style="padding: 0;"><%= id%></td>
                <td><%= tpPlano%></td>
                <td><%= benefQtdCursos%></td>
                <td><%= benefDescPlno%></td>
                <td><%= precoFixo%></td>
                <td><%= precoQtdProdutores%></td>

                <td><form action="alterarAss" id="alterar">
                    <input type="hidden" value="<%= i%>" name="i">
                    <button type="submit"><img src="assets/alterar.svg"></button>
                </form></td>
                <td>
                    <form action="deletarAss" method="post" id="deletar">
                        <input type="hidden" value="<%= id%>" name="id">
                        <button type="submit"><img src="assets/deletar.svg"></button>
                    </form></td>

            </tr>
            <%
                }%>
            </tbody>
        </table>
    </div>

    <a href="${pageContext.request.contextPath}/html/adicionarAssinatura.html">
        <div id="adicionar" style="margin-top: 20px">
            <p>+Adicionar</p>
        </div>
    </a>
</div>
<script>
    // Configuração das colunas - indice começa em 0
    const configTabela = {
        campos: {
            'id': 0,
            'tpplano': 1,
            'benefqtdcursos': 2,
            'benefdescplno': 3,
            'precofixo': 4,
            'precoqtdprodutores': 5
        },
        colunasAcoes: 2 // ultimas colunas nao entram na busca
    };

    // Inicia tudo quando carregar a pagina
    window.onload = function() {
        setTimeout(iniciarSistemaBusca, 1000);
    };

    function iniciarSistemaBusca() {
        let campo = document.getElementById('campoBusca');
        if (!campo) {
            console.log('campo busca nao achado, tentando dnv...');
            setTimeout(iniciarSistemaBusca, 500);
            return;
        }

        // Quando digita, busca
        campo.oninput = function(e) {
            fazerBusca(e.target.value);
        };

        // Se tiver form, nao deixa recarregar
        let form = campo.closest('form');
        if (form) {
            form.onsubmit = function(e) {
                e.preventDefault();
                fazerBusca(campo.value);
            };
        }

        pintarLinhasAlternadas();
    }

    function fazerBusca(termo) {
        let tabela = document.getElementById('tabela');
        if (!tabela) {
            console.log('erro: tabela nao existe');
            return;
        }

        let linhas = pegarLinhasComDados(tabela);
        if (linhas.length === 0) return;

        termo = termo.trim();
        removerDestaquesAnteriores();

        if (termo === '') {
            mostrarTodasAsLinhas();
            return;
        }

        // Verifica se é busca especifica de coluna
        if (termo.indexOf(':') > -1) {
            let partes = termo.split(':');
            let nomeCampo = partes[0].toLowerCase().trim();
            let valorBusca = partes.slice(1).join(':').trim();

            let indiceColuna = configTabela.campos[nomeCampo];
            if (indiceColuna !== undefined) {
                buscarNaColuna(linhas, indiceColuna, valorBusca);
            } else {
                // Se nao achou o campo, busca normal
                buscarGeral(linhas, termo);
            }
        } else {
            buscarGeral(linhas, termo);
        }

        pintarLinhasAlternadas();
    }

    function pegarLinhasComDados(tabela) {
        let todasLinhas = tabela.getElementsByTagName('tr');
        let linhasValidas = [];

        for (let i = 0; i < todasLinhas.length; i++) {
            let linha = todasLinhas[i];

            // Pula cabecalho e linhas de titulo
            if (linha.closest('thead')) continue;
            if (linha.querySelector('th')) continue;
            if (linha.classList.contains('header')) continue;

            // So adiciona se tiver células de dados
            if (linha.querySelector('td')) {
                linhasValidas.push(linha);
            }
        }

        return linhasValidas;
    }

    function buscarNaColuna(linhas, indiceColuna, valor) {
        for (let i = 0; i < linhas.length; i++) {
            let linha = linhas[i];
            let celulas = linha.getElementsByTagName('td');
            let achou = false;

            if (celulas.length > indiceColuna) {
                let celula = celulas[indiceColuna];
                let texto = celula.textContent || celula.innerText || '';

                if (texto.toLowerCase().includes(valor.toLowerCase())) {
                    achou = true;
                    destacarTexto(celula, valor);
                }
            }

            linha.style.display = achou ? '' : 'none';
        }
    }

    function buscarGeral(linhas, termo) {
        let termoLower = termo.toLowerCase();

        for (let i = 0; i < linhas.length; i++) {
            let linha = linhas[i];
            let celulas = linha.getElementsByTagName('td');
            let achou = false;

            // Não busca nas colunas de ação
            for (let j = 0; j < celulas.length - configTabela.colunasAcoes; j++) {
                let celula = celulas[j];
                let texto = celula.textContent || celula.innerText || '';

                if (texto.toLowerCase().includes(termoLower)) {
                    achou = true;
                    destacarTexto(celula, termo);
                    // não break pra poder destacar em todas as colunas
                }
            }

            linha.style.display = achou ? '' : 'none';
        }
    }

    function destacarTexto(celula, termoBusca) {
        if (!termoBusca.trim()) return;

        let textoOriginal = celula.textContent || celula.innerText || '';
        let termo = termoBusca.toLowerCase();

        if (!textoOriginal.toLowerCase().includes(termo)) return;

        let novoHTML = '';
        let texto = textoOriginal;

        while (true) {
            let posicao = texto.toLowerCase().indexOf(termo);
            if (posicao === -1) break;

            let antes = texto.substring(0, posicao);
            let match = texto.substring(posicao, posicao + termo.length);
            texto = texto.substring(posicao + termo.length);

            novoHTML += antes + '<mark class="destaque">' + match + '</mark>';
        }

        novoHTML += texto;
        celula.innerHTML = novoHTML;
    }

    function pintarLinhasAlternadas() {
        let tabela = document.getElementById('tabela');
        if (!tabela) return;

        let linhas = pegarLinhasComDados(tabela);
        let count = 0;

        // Remove cores antigas
        for (let i = 0; i < linhas.length; i++) {
            linhas[i].classList.remove('linha-par', 'linha-impar');
        }

        // Aplica novas cores só nas visíveis
        for (let i = 0; i < linhas.length; i++) {
            if (linhas[i].style.display !== 'none') {
                if (count % 2 === 0) {
                    linhas[i].classList.add('linha-par');
                } else {
                    linhas[i].classList.add('linha-impar');
                }
                count++;
            }
        }
    }

    function removerDestaquesAnteriores() {
        let tabela = document.getElementById('tabela');
        if (!tabela) return;

        let destaques = tabela.querySelectorAll('.destaque');
        for (let i = 0; i < destaques.length; i++) {
            let destaque = destaques[i];
            let pai = destaque.parentNode;
            if (pai) {
                let textoNormal = document.createTextNode(destaque.textContent);
                pai.replaceChild(textoNormal, destaque);
            }
        }
    }

    function mostrarTodasAsLinhas() {
        let tabela = document.getElementById('tabela');
        if (!tabela) return;

        let linhas = pegarLinhasComDados(tabela);
        for (let i = 0; i < linhas.length; i++) {
            linhas[i].style.display = '';
        }
        pintarLinhasAlternadas();
    }

    // Backup caso o onload nao funcione direito
    setTimeout(function() {
        let campo = document.getElementById('campoBusca');
        if (campo && !campo.oninput) {
            campo.oninput = function(e) {
                fazerBusca(e.target.value);
            };
        }
    }, 3000);
</script>

<style>

    #tabela tr,
    #tabela tr:nth-child(n),
    #tabela tr:nth-child(odd),
    #tabela tr:nth-child(even) {
        background-color: transparent !important;
        background: transparent !important;
    }

    #tabela{
        width: 925px;
    }

    #tabela tr.linha-par {
        background-color: #fcf7f1 !important;
        background: #fcf7f1 !important;
    }

    #tabela tr.linha-impar {
        background-color: rgba(173, 173, 173, 1) !important;
        background: rgba(173, 173, 173, 1) !important;
    }

    #tabela tr.linha-par td,
    #tabela tr.linha-impar td {
        background-color: inherit !important;
        background: inherit !important;
    }

    .highlight-text {
        background-color: yellow !important;
        color: black !important;
        font-weight: bold !important;
        padding: 1px 2px !important;
        border-radius: 2px !important;
    }

</style>
</body>
</html>
