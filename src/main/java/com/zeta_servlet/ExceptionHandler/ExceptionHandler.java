package com.zeta_servlet.ExceptionHandler;


import java.util.ArrayList;
import java.util.List;
    public class ExceptionHandler{

        private static List<Exception> excecoes = new ArrayList<>();

        private Exception excecao;
        //    SQLException se;
        //    NullPointerException npe;
        //    IndexOutOfBoundsException iofbe;
        //    IOException ioe;
        //    InputMismatchException ime;
        //    ArrayIndexOutOfBoundsException aiofbe;
        //    NumberFormatException nfe;
        //    FileNotFoundException fnfe;
        //    RuntimeException re;


        public ExceptionHandler(Exception excecao) {
        this.excecao = excecao;
        excecoes.add(excecao);
        }


        public void printExeption(){
            if (!excecoes.isEmpty()) {
                System.out.println("\n"+"-".repeat(30));
                System.out.println("LISTA DE EXCEÇÕES:");
                System.out.println("EXCEÇÕES CAPTURADAS --- "+excecoes.size()+"\n");

                for (int i = 0; i < excecoes.size(); i++) {

//                    Throwable cause = excecoes.get(i).getCause();
//                    String nome = excecoes.get(i).getClass().getSimpleName();
//                    String mensagem = excecoes.get(i).getMessage();
//                    StackTraceElement[] stackTraceElements = excecoes.get(i).getStackTrace();
//
//                    if (stackTraceElements.length > 0) {
//                        StackTraceElement elemento = stackTraceElements[0];
//                        String fileNome = elemento.getFileName();
//                        int lineNumero = elemento.getLineNumber();
//                        String metodoNome = elemento.getMethodName();
//
//                        System.out.println("Exceção: " + nome);
//                        System.out.println("Mensagem: " + mensagem);
//                        System.out.println("Metodo: "+metodoNome);
//                        System.out.println("Classe: "+fileNome);
//                        System.out.println("Linha: "+lineNumero);
//                    }
//
//                    else{
//                        System.out.println("Exceção: " + nome);
//                        System.out.println("Mensagem: " + mensagem);
//                    }
//
//                    if (cause != null) {
//                        System.out.println("Causa: " + cause.getClass().getSimpleName());
//                    }
                    excecoes.get(i).getStackTrace();
                    System.out.println("\n"+"=".repeat(30));
                }
        }
    }
}
