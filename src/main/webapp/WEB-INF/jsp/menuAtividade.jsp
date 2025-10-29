<%@ page import="java.util.List" %>
<%@ page import="com.zeta_servlet.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuAdministrador.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">

  <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
  <img src="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
  <ul>
    <li><form action="home" method="post" id="fHome"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudHome.svg" alt="home">Home</button></form></li>
    <li><form action="menuAdm" method="post" id="fAdm"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAdm.svg" alt="Adm">Administrador</button></form></li>
    <li><form action="menuAss" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAss.svg" alt="Assinatura">Assinatura</button></form></li>
    <li><a href="${pageContext.request.contextPath}/html/menuProdutor.html"><img src="${pageContext.request.contextPath}/assets/crudProd.svg" alt="Produtor">Produtor</a></li>
    <li><form action="menuAtividade" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudAtiv.svg" alt="Atividade">Atividade</button></form></li>
    <li><a href="${pageContext.request.contextPath}/html/menuAula.html"><img src="${pageContext.request.contextPath}/assets/crudAula.svg" alt="Aula">Aula</a></li>
    <li><a href="../.."><img src="${pageContext.request.contextPath}/assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
    <li><form action="logout" method="post" id="fAdm"><button type="submit"><img src="${pageContext.request.contextPath}/assets/exit.svg" alt="exit">Sair</button></form></li>

  </ul>
</nav>
<div id="consultar">
  <h1>Atividade</h1>
  <div id="query">
    <div id="buscas">
      <label for="campoBusca"><img src="${pageContext.request.contextPath}/assets/lupa.svg"></label>
      <input type="text" name="index" id="campoBusca" placeholder="Buscar, ou inserir código de busca (ex: email:, id:)" >
    </div>
  </div>

  </table>
  <div id="tabela">
    <table style="padding: 0; margin: 0;">
      <thead style="border-radius: 12px; position: sticky; z-index: 20; top: 0;">
      <tr style="border-radius: 12px">
        <td class="name-title" style="text-align: center">ID</td>
        <td class="name-title" style="text-align: center">Pontuação</td>
        <td class="name-title" style="text-align: center;">Pergunta</td>
        <td class="name-title" style="text-align: center;">Alternativa</td>
        <td class="name-title" style="text-align: center;">Correta</td>
        <td class="name-title" style="text-align: center;">Id_Aula</td>
        <td class="name-title" style="text-align: center;"></td>
      </tr>
      </thead>
      <tbody style="border-radius: 12px">
      <%
        @SuppressWarnings("unchecked")
        List<Atividade> lisAti = (List<Atividade>) request.getAttribute("list");
        System.out.println(lisAti);
        for (int i = 0; i < lisAti.size(); i++) {
          Double pontuacao = lisAti.get(i).getPontuacao();
          List<Pergunta> pergunta = lisAti.get(i).getPerguntas();
          int id = lisAti.get(i).getId();
          List<Alternativa> alternativas = lisAti.get(i).getAlternativas();
          Boolean correta = lisAti.get(i).getAlternativas().get(i).isCorreto();
          int id_aula = lisAti.get(i).getId_aula();

      %>
      <tr style="padding: 0; border-radius: 12px">
        <td style="padding: 0;"><%= id%></td>
        <td><%= pontuacao%></td>
        <td><%= pergunta%></td>
        <td><%= alternativas%></td>
        <td><%= correta%></td>
        <td><%= id_aula%></td>
        <td><form action="alterarAtividade" id="alterar">
          <input type="hidden" value="<%= i%>" name="i">
          <button type="submit"><img src="${pageContext.request.contextPath}/assets/alterar.svg"></button>
        </form>

          <form action="deletarAtividade" method="post" id="deletar">
            <input type="hidden" value="<%= id%>" name="id">
            <button type="submit"><img src="${pageContext.request.contextPath}/assets/deletar.svg"></button>
          </form></td>

      </tr>
      <%
        }%>
      </tbody>
    </table>
  </div>

  <a href="${pageContext.request.contextPath}/html/adicionarAtividade.html">
    <div id="adicionar" style="margin-top: 20px">
      <p>+ Adicionar Atividade</p>
    </div>
  </a>
</div>
<script>
  const CONFIG_TABELA = {
    campos: {
      'id': 0,
      'pontuacao': 1,
      'pergunta': 2,
      'alternativa': 3,
      'correta': 4,
      'id_aula': 5
    },
    colunasAcoes: 1, // Quantidade de colunas de ações no final
    totalColunas: 6  // Total de colunas de dados (opcional, para validação)
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

      const linhas = obterLinhasDados(tabela);
      if (!linhas || linhas.length === 0) return;

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

        // VERIFICAÇÃO ADICIONADA - se o campo existe na configuração
        if (indiceColuna !== undefined && indiceColuna >= 0) {
          executarBuscaColuna(linhas, indiceColuna, valor);
        } else {
          executarBuscaGeral(linhas, termo);
        }
      } else {
        executarBuscaGeral(linhas, termo);
      }

      aplicarCoresLinhas();

    } catch (error) {
      console.log('Erro na busca:', error);
    }
  }

  function obterLinhasDados(tabela) {
    try {
      const todasLinhas = tabela.getElementsByTagName('tr');
      const linhasDados = [];

      for (let i = 0; i < todasLinhas.length; i++) {
        const linha = todasLinhas[i];

        if (linha.closest('thead')) continue;
        if (linha.querySelector('th') || linha.classList.contains('header')) continue;
        if (linha.querySelector('td')) {
          linhasDados.push(linha);
        }
      }

      return linhasDados;

    } catch (error) {
      console.log('Erro ao buscar linhas:', error);
      return [];
    }
  }

  function executarBuscaColuna(linhas, indiceColuna, valorBusca) {
    try {
      for (let i = 0; i < linhas.length; i++) {
        const linha = linhas[i];
        const celulas = linha.getElementsByTagName('td');
        let encontrado = false;

        // VERIFICAÇÃO DE SEGURANÇA ADICIONADA
        if (celulas.length > indiceColuna && indiceColuna >= 0) {
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

  function executarBuscaGeral(linhas, termoBusca) {
    try {
      const termo = termoBusca.toLowerCase();

      for (let i = 0; i < linhas.length; i++) {
        const linha = linhas[i];
        const celulas = linha.getElementsByTagName('td');
        let encontrado = false;

        // CORRIGIDO: Desconta colunas de ações em TODOS os acessos
        const totalColunasBusca = celulas.length - CONFIG_TABELA.colunasAcoes;

        for (let j = 0; j < totalColunasBusca; j++) {
          const celula = celulas[j];
          const textoCelula = (celula.textContent || celula.innerText || '').toLowerCase();

          if (textoCelula.includes(termo)) {
            encontrado = true;
            aplicarDestaqueSeguro(celula, termoBusca);
            // break; // Opcional: parar na primeira ocorrência
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
      if (!tabela) return;

      const linhas = obterLinhasDados(tabela);
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
      if (!tabela) return;

      const linhas = obterLinhasDados(tabela);
      for (let i = 0; i < linhas.length; i++) {
        linhas[i].style.display = '';
      }

      aplicarCoresLinhas();
    } catch (error) {
      console.log('Erro ao mostrar linhas:', error);
    }
  }

  // Função auxiliar para verificar a estrutura da tabela
  function debugEstruturaTabela() {
    const tabela = document.getElementById('tabela');
    if (!tabela) {
      console.log('Tabela não encontrada');
      return;
    }

    const linhas = obterLinhasDados(tabela);
    if (linhas.length > 0) {
      const primeiraLinha = linhas[0];
      const celulas = primeiraLinha.getElementsByTagName('td');
      console.log('Colunas encontradas:', celulas.length);
      console.log('Configuração esperada:', CONFIG_TABELA);
    }
  }

  window.addEventListener('load', function() {
    setTimeout(iniciarBusca, 500);
    // debugEstruturaTabela(); // Descomente para debug
  });
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
    background-color: aqua !important;
    opacity: 0.5;
    color: black !important;
    font-weight: bold !important;
    padding: 1px 2px !important;
    border-radius: 2px !important;
  }

</style>
</body>
</html>
