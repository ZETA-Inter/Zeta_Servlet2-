package com.zeta_servlet.Utils;

public class Regex {
    //MÉTODOS DE VERIFICAÇÃO
    //Valida o email via regex
    public boolean validarEmail(String email){
        if (email.matches("^[a-z][a-z0-9+-_.].+@[a-z].+\\.(com|me|org|br)$")){
            return true;
        }
        return false;
    }
//Valida o cpf via regex
    public boolean validarCpf(String cpf){
        if (cpf.matches("^[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{2}$")){
            return true;
        }
        return false;
    }
// Valida o CNPJ via regex
    public boolean validarCnpj(String cnpj){
        if (cnpj.matches("^[0-9]{2}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{3}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{3}( | \\.|\\. | \\. |\\.| -|- | - |-|\\/| \\/| \\/ |\\/ )?[0-9]{4}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{2}$")){
            return true;
        }
        return false;
    }
//Valida a senha via regex
    public boolean validarSenha(String senha){
        if (senha.matches("^([a-z](?=.[A-Z])|[A-Z](?=.[a-z]))(?=.[0-9])(?=.[!@#$%¨&*()_+=-\\[\\]\\{\\}\\\\\\/] \\.\\,).{7,}$")){
            return true;
        }
        return false;
    }
//Valida o telefone via regex
    public boolean validarTelefone(String telefone){
        if (telefone.matches("^(\\+[0-9]{2}|[0-9]{2})?(\\([0-9]{2}\\)|[0-9]{2})( |-)?9[0-9]{4}( |-)?[0-9]{4}")){
            return true;
        }
        return false;
    }

    //METODOS DE FORMATAÇÃO
// retira outros caracteres não numericos do cpf via regex
    public String formatarCpf(String cpf){
        if (validarCpf(cpf)){
            String cpfLimpo = cpf.replaceAll("[^0-9]", "");
            return cpfLimpo;
        }
        return "";
    }
// retira caracteres desnecessarios do cnpj
    public String formatarCnpj(String cnpj){
        if (validarCnpj(cnpj)){
            String cnpjLimpo = cnpj.replaceAll("(\\.|\\-)", "");
            return cnpjLimpo;
        }
        return "";
    }
// retira caracteres desnecessarios do telefone
    public String formatarTelefone(String telefone){
        if (validarTelefone(telefone)){
            String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
            return telefoneLimpo;
        }
        return "";
    }
}
