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
                    excecoes.get(i).getStackTrace();
                    System.out.println("\n"+"=".repeat(30));
                }
        }
    }
}
