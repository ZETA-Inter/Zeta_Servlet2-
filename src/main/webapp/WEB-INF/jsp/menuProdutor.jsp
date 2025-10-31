<%@ page import="com.zeta_servlet.model.Produtor" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuProdutor.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" type="image/x-icon">
  <title>CRUD</title>
</head>
<body>
<nav class="nav-bar">
  <img src="${pageContext.request.contextPath}/assets/LOGO%20ZETA%20-%205.png" alt="Logo" id="logoMenu">
  <ul>
    <li><a href="${pageContext.request.contextPath}home.html"><img src="${pageContext.request.contextPath}/assets/crudHome.svg" alt="Home">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/menuAdministrador.jsp"><img src="${pageContext.request.contextPath}/assets/crudAdm.svg" alt="Adm">Administrador</a></li>
    <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/menuAssinatura.jsp"><img src="${pageContext.request.contextPath}/assets/crudAss.svg" alt="Assinatura">Assinatura</a></li>
    <li><form action="menuProdutor" method="post"><button type="submit"><img src="${pageContext.request.contextPath}/assets/crudProd.svg" alt="Produtor">Produtor</button></form></li>
    <li><a href="${pageContext.request.contextPath}/WEB-INF/jsp/menuAtividade.jsp"><img src="${pageContext.request.contextPath}/assets/crudAtiv.svg" alt="Atividade">Atividade</a></li>
    <li><a href="${pageContext.request.contextPath}/html/menuAula.html"><img src="${pageContext.request.contextPath}/assets/crudAula.svg" alt="Aula">Aula</a></li>
    <li><a href=".."><img src="${pageContext.request.contextPath}/assets/crudDash.svg" alt="Dashboards">Dashboards</a></li>
    <li><a href="#"><img src="${pageContext.request.contextPath}/assets/exit.svg" alt="exit">Sair</a></li>
  </ul>
</nav>
<div id="consultar">
  <h1>Produtor</h1>
  <form action="">
    <label for="buscar"><img src="${pageContext.request.contextPath}/assets/lupa.svg"></label>
    <input type="text" name="buscar" id="buscar" placeholder="Buscar" >
  </form>

  </table>
  <div id="tabela">
    <table style="padding: 0; margin: 0;">
      <thead style="border-radius: 12px; position: sticky; z-index: 20; top: 0;">
      <tr style="border-radius: 12px">
        <td class="name-title" style="text-align: center">ID</td>
        <td class="name-title" style="text-align: center">CPF</td>
        <td class="name-title" style="text-align: center;">Data de Nascimento</td>
        <td class="name-title" style="text-align: center;">Email</td>
        <td class="name-title" style="text-align: center;">Senha</td>
        <td class="name-title" style="text-align: center;">Nome</td>
        <td class="name-title" style="text-align: center;">Último nome</td>
        <td class="name-title" style="text-align: center;">Aulas Feitas</td>
        <td class="name-title" style="text-align: center;">Id Fornecedor</td>
        <td class="name-title" style="text-align: center;">Id Assinatura</td>
        <td class="name-title" style="text-align: center;"></td>
      </tr>
      </thead>
      <tbody style="border-radius: 12px">
      <%
        @SuppressWarnings("unchecked")
        List<Produtor> lisProd = (List<Produtor>) request.getAttribute("list");
        for (int i = 0; i < lisProd.size(); i++) {
          System.out.println(i);
          int id = lisProd.get(i).getId();
          String cpf = lisProd.get(i).getCpf();
          LocalDate dtNasci = lisProd.get(i).getDt_nascimento();
          String email = lisProd.get(i).getEmail();
          String senha = lisProd.get(i).getSenha();
          String nome = lisProd.get(i).getNome_primeiro();
          String ultNome = lisProd.get(i).getNome_ultimo();
          int aulasFeitas = lisProd.get(i).getAulas_feitas();
          int idFornecedor = lisProd.get(i).getId_fornecedor();
          int idAssinatura = lisProd.get(i).getId_assinatura();
      %>
      <tr style="padding: 0; border-radius: 12px">
        <td style="padding: 0;"><%= id%></td>
        <td><%= cpf%></td>
        <td><%= dtNasci%></td>
        <td><%= email%></td>
        <td><%= senha%></td>
        <td><%= nome%></td>
        <td><%= ultNome%></td>
        <td><%= aulasFeitas%></td>
        <td><%= idFornecedor%></td>
        <td><%= idAssinatura%></td>
        <td><form action="${pageContext.request.contextPath}/PrepAlterarProdutor" id="alterar">
          <input type="hidden" value="<%= i%>" name="i">
          <button type="submit"><img src="${pageContext.request.contextPath}/assets/alterar.svg"></button>
        </form>

          <form action="${pageContext.request.contextPath}/DeletarProdutor" method="post" id="deletar">
            <input type="hidden" value="<%= id%>" name="id">
            <button type="submit"><img src="${pageContext.request.contextPath}/assets/deletar.svg"></button>
          </form></td>

      </tr>
      <%
        }%>
      </tbody>
    </table>
  </div>

  <a href="${pageContext.request.contextPath}/html/adicionarProdutor.html">
    <div id="adicionar">
      <p>+ Adicionar Produtor</p>
    </div>
  </a>

  <script>
    const container = document.querySelector('.conteiner');
    let lastScrollLeft = 0;
    let lastScrollTop = 0;
    let scrollLock = false;

    // CORREÇÃO: Scroll suave sem travar
    container.addEventListener('scroll', () => {
      if (scrollLock) return;

      const deltaX = container.scrollLeft - lastScrollLeft;
      const deltaY = container.scrollTop - lastScrollTop;

      // Atualiza as últimas posições
      lastScrollLeft = container.scrollLeft;
      lastScrollTop = container.scrollTop;

      clearTimeout(container._scrollTimeout);
      container._scrollTimeout = setTimeout(() => {
        // Reset após scroll parar
      }, 150);
    });

    const CONFIG_TABELA = {
      campos: {
        'id': 0,
        'cpf': 1,
        'dtNasci': 2,
        'email': 3,
        'senha': 4,
        'nome': 5,
        'ultNome': 6,
        'aulasFeitas': 7,
        'idFornecedor': 8,
        'idAssinatura': 9,
      },
      colunasAcoes: 1,
      totalColunas: 10
    };

    // ... o resto do código de busca permanece igual
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

          const totalColunasBusca = celulas.length - CONFIG_TABELA.colunasAcoes;

          for (let j = 0; j < totalColunasBusca; j++) {
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
    });
  </script>
</div>
</body>
</html>