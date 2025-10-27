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
    <div id="query">
        <div id="buscas">
            <label for="campoBusca"><img src="assets/lupa.svg"></label>
            <input type="text" name="index" id="campoBusca" placeholder="Buscar, ou inserir código de busca (ex: email:, id:)" >
        </div>
    </div>
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
<script>
    //para configurar a tabela adicione os valores aqui:
    const CONFIG_TABELA = {
        campos: {
            'id': 0,
            'email': 1,
            'senha': 2
        },
        //colunas com ações, o padrão é sempre 1
        colunasAcoes: 1
    };

    function iniciarBusca() {
        setTimeout(function() {
            try {
                const campoBusca = document.getElementById('campoBusca');
                if (!campoBusca) {
                    setTimeout(iniciarBusca, 500);
                    return;
                }

                campoBusca.addEventListener('input', function(e) {
                    executarBusca(e.target.value);
                });

                const formulario = campoBusca.closest('form');
                if (formulario) {
                    formulario.addEventListener('submit', function(e) {
                        e.preventDefault();
                        executarBusca(campoBusca.value);
                    });
                }

                aplicarCoresLinhas();

            } catch (error) {
                console.log('Erro na inicialização:', error);
            }
        }, 300);
    }

    function executarBusca(termoBusca) {
        try {
            const tabela = document.getElementById('tabela');
            if (!tabela) return;

            const linhas = tabela.getElementsByTagName('tr');
            if (!linhas) return;

            const termo = termoBusca.trim();

            removerDestaques();

            if (!termo) {
                mostrarTodasLinhas();
                return;
            }

            if (termo.includes(':')) {
                const partes = termo.split(':');
                const campo = partes[0].toLowerCase().trim();
                const valor = partes.slice(1).join(':').trim();

                const indiceColuna = CONFIG_TABELA.campos[campo];

                if (indiceColuna !== undefined) {
                    executarBuscaColuna(indiceColuna, valor);
                } else {
                    executarBuscaGeral(termo);
                }
            } else {
                executarBuscaGeral(termo);
            }

            aplicarCoresLinhas();

        } catch (error) {
            console.log('Erro na busca:', error);
        }
    }

    function executarBuscaColuna(indiceColuna, valorBusca) {
        try {
            const tabela = document.getElementById('tabela');
            const linhas = tabela.getElementsByTagName('tr');

            for (let i = 0; i < linhas.length; i++) {
                const linha = linhas[i];
                const celulas = linha.getElementsByTagName('td');
                let encontrado = false;

                if (celulas.length > indiceColuna) {
                    const celula = celulas[indiceColuna];
                    const textoCelula = (celula.textContent || celula.innerText || '').toLowerCase();
                    const termoBusca = valorBusca.toLowerCase();

                    if (textoCelula.includes(termoBusca)) {
                        encontrado = true;
                        aplicarDestaqueSeguro(celula, valorBusca);
                    }
                }

                linha.style.display = encontrado ? '' : 'none';
            }

        } catch (error) {
            console.log('Erro na busca por coluna:', error);
        }
    }

    function executarBuscaGeral(termoBusca) {
        try {
            const tabela = document.getElementById('tabela');
            const linhas = tabela.getElementsByTagName('tr');
            const termo = termoBusca.toLowerCase();

            for (let i = 0; i < linhas.length; i++) {
                const linha = linhas[i];
                const celulas = linha.getElementsByTagName('td');
                let encontrado = false;

                for (let j = 0; j < celulas.length - CONFIG_TABELA.colunasAcoes; j++) {
                    const celula = celulas[j];
                    const textoCelula = (celula.textContent || celula.innerText || '').toLowerCase();

                    if (textoCelula.includes(termo)) {
                        encontrado = true;
                        aplicarDestaqueSeguro(celula, termoBusca);
                    }
                }

                linha.style.display = encontrado ? '' : 'none';
            }

        } catch (error) {
            console.log('Erro na busca geral:', error);
        }
    }

    function aplicarDestaqueSeguro(celula, termoBusca) {
        try {
            if (!termoBusca.trim()) return;

            const textoOriginal = celula.textContent || celula.innerText || '';
            const termo = termoBusca.toLowerCase();

            if (!textoOriginal.toLowerCase().includes(termo)) return;

            let resultado = '';
            let textoRestante = textoOriginal;

            while (true) {
                const indice = textoRestante.toLowerCase().indexOf(termo);
                if (indice === -1) break;

                const antes = textoRestante.substring(0, indice);
                const correspondente = textoRestante.substring(indice, indice + termo.length);
                textoRestante = textoRestante.substring(indice + termo.length);

                resultado += antes + '<span class="highlight-text">' + correspondente + '</span>';
            }

            resultado += textoRestante;
            celula.innerHTML = resultado;

        } catch (error) {
            console.log('Erro no destaque:', error);
        }
    }

    function aplicarCoresLinhas() {
        try {
            const tabela = document.getElementById('tabela');
            const linhas = tabela.getElementsByTagName('tr');
            let contadorLinhasVisiveis = 0;

            for (let i = 0; i < linhas.length; i++) {
                const linha = linhas[i];
                linha.classList.remove('linha-par', 'linha-impar');
            }

            for (let i = 0; i < linhas.length; i++) {
                const linha = linhas[i];

                if (linha.style.display !== 'none') {
                    if (contadorLinhasVisiveis % 2 === 0) {
                        linha.classList.add('linha-par');
                    } else {
                        linha.classList.add('linha-impar');
                    }
                    contadorLinhasVisiveis++;
                }
            }

        } catch (error) {
            console.log('Erro ao aplicar cores:', error);
        }
    }

    function removerDestaques() {
        try {
            const tabela = document.getElementById('tabela');
            if (!tabela) return;

            const destaques = tabela.querySelectorAll('.highlight-text');
            for (let i = 0; i < destaques.length; i++) {
                const destaque = destaques[i];
                const pai = destaque.parentNode;
                if (pai) {
                    const texto = document.createTextNode(destaque.textContent);
                    pai.replaceChild(texto, destaque);
                }
            }
        } catch (error) {
            console.log('Erro ao remover destaques:', error);
        }
    }

    function mostrarTodasLinhas() {
        try {
            const tabela = document.getElementById('tabela');
            const linhas = tabela.getElementsByTagName('tr');

            for (let i = 0; i < linhas.length; i++) {
                linhas[i].style.display = '';
            }

            aplicarCoresLinhas();
        } catch (error) {
            console.log('Erro ao mostrar linhas:', error);
        }
    }

    window.addEventListener('load', function() {
        setTimeout(iniciarBusca, 500);
    });

    setTimeout(function() {
        const campoBusca = document.getElementById('campoBusca');
        if (campoBusca) {
            campoBusca.addEventListener('input', function(e) {
                executarBusca(e.target.value);
            });
        }
    }, 2000);
</script>

<style>

    #tabela tr,
    #tabela tr:nth-child(n),
    #tabela tr:nth-child(odd),
    #tabela tr:nth-child(even) {
        background-color: transparent !important;
        background: transparent !important;
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
