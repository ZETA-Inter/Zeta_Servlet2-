package com.zeta_servlet.Utils;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.Aula;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Filtro {

    public static  <T> boolean contemTexto(T objeto, String textoBusca) {
        if (objeto == null) return false;

        try {
            Field[] campos = objeto.getClass().getDeclaredFields();

            for (Field campo : campos) {
                campo.setAccessible(true);

                Object valor = campo.get(objeto);

                if (valor != null) {
                    String valorString = String.valueOf(valor);
                    if (valorString.toLowerCase().contains(textoBusca.toLowerCase())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }

        return false;
    }

    public <T> List<T> buscarPorTexto(List<T> lista, String textoBusca) {
        List<T> resultado = new ArrayList<>();

        if (lista == null || textoBusca == null || textoBusca.trim().isEmpty()) {
            return resultado;
        }

        for (T objeto : lista) {
            if (contemTexto(objeto, textoBusca)) {
                resultado.add(objeto);
            }
        }

        return resultado;
    }


    // utiliza hashMap, automaticamente excluindo IDs iguais
    public <T> List<T> removerDuplicados(List<T> lista) {
        Map<Integer, T> map = new HashMap<>();
        try {
            if (lista == null || lista.isEmpty()) {
                return new ArrayList<>();
            }

            // Map que automaticamente remove duplicados
            for (T item : lista) {
                Method metodoGetId = item.getClass().getMethod("getId");
                int id = (int) metodoGetId.invoke(item);
                map.put(id, item);
                // Se ID já existe, substitui pelo último
            }
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
        finally {
            return new ArrayList<>(map.values());
        }
    }
}