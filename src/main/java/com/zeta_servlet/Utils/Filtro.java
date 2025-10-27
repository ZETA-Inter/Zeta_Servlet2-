package com.zeta_servlet.Utils;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import java.lang.reflect.Field;

public class Filtro {

    private static <T> boolean contemTexto(T objeto, String textoBusca) {
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
}
