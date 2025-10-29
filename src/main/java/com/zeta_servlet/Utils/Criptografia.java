package com.zeta_servlet.Utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Random;

public class Criptografia {

    Dotenv dotenv = Dotenv.configure().load();
    private String senha = dotenv.get("PASSWORD_CRIPTO");
    Random random = new Random();
    int safeNum = random.nextInt(1000);
    private final int MIN = 32;
    private final int MAX = 126;
    private final int RANGE = MAX - MIN + 1;




        public String criptografar(String mensagem) {
            if (mensagem == null || senha == null || senha.isEmpty()) {
                return mensagem;
            }

            StringBuilder resultado = new StringBuilder();
            char[] charsMensagem = mensagem.toCharArray();
            char[] charsSenha = senha.toCharArray();
            int indexSenha = safeNum;

            for (char charMensagem : charsMensagem) {
                char charSenha = charsSenha[indexSenha % charsSenha.length];

                int posMensagem = charMensagem - MIN;
                int posSenha = charSenha - MIN;

                int soma = posMensagem + posSenha;
                int posResultado = soma % RANGE;

                char caractereCriptografado = (char) (posResultado + MIN);

                resultado.append(caractereCriptografado);
                indexSenha++;
            }

            return resultado.toString();
        }

    }




