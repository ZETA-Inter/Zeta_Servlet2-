package com.zeta_servlet.Utils;

import org.jetbrains.annotations.NotNull;

public class Regex {
    //MÉTODOS DE VERIFICAÇÃO
    //Valida o email via regex
    public boolean validarEmail(@NotNull String email){
        if (email.matches("^[a-z][a-z0-9+-_.].+@[a-z].+\\.(com|me|org|br)$")){
            return true;
        }
        return false;
    }
//Valida o cpf via regex
    public boolean validarCpf(@NotNull String cpf){
        if (cpf.matches("^[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{2}$")){
            return true;
        }
        return false;
    }
// Valida o CNPJ via regex
    public boolean validarCnpj(@NotNull String cnpj){
        if (cnpj.matches("^[0-9]{2}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{3}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{3}( | \\.|\\. | \\. |\\.| -|- | - |-|\\/| \\/| \\/ |\\/ )?[0-9]{4}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{2}$")){
            return true;
        }
        return false;
    }
//Valida a senha via regex
    public boolean validarSenha(@NotNull String senha){
        if (senha.matches("^([a-z](?=.*[A-Z])|[A-Z](?=.*[a-z]))(?=.*[0-9])(?=.*[!@#$%¨&*()_+=\\-\\[\\]\\{\\}\\\\\\/ \\.,]).{7,}$")){
            return true;
        }
        return false;
    }
//Valida o telefone via regex
    public boolean validarTelefone(@NotNull String telefone){
        if (telefone.matches("^(\\+[0-9]{2}|[0-9]{2})?(\\([0-9]{2}\\)|[0-9]{2})( |-)?9[0-9]{4}( |-)?[0-9]{4}")){
            return true;
        }
        return false;
    }

    //METODOS DE FORMATAÇÃO
// retira outros caracteres não numericos do cpf via regex ou Coloca Caracteres
    public String formatarCpf(String cpf){
        if (validarCpf(cpf) && cpf.matches(".*[.\\-/].*")){
            String cpfLimpo = cpf.replaceAll("[^0-9]", "");
            return cpfLimpo;
        }
        else if (validarCpf(cpf) && !cpf.contains("\\.\\-\\/")) {
            return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return "";
    }


    // retira caracteres desnecessarios do cnpj
    public String formatarCnpj(String cnpj){
        if (validarCnpj(cnpj) && cnpj.matches(".*[.\\-].*")){
            String cnpjLimpo = cnpj.replaceAll("\\D", "");
            return cnpjLimpo;
        }
        else if (validarCnpj(cnpj) && !cnpj.matches(".*[.\\-].*")) {
            return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");

        }
        return "";
    }
// retira caracteres desnecessarios do telefone ou recoloca caracteres
    public String formatarTelefone(String telefone){
        if (validarTelefone(telefone) && telefone.matches(".*[ .\\-()].*")){
            String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
            return telefoneLimpo;
        }
        else if (validarTelefone(telefone) && !telefone.matches(".*[ .\\-()].*")) {
            return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        }
        return "";
    }
}

