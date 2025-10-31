package com.zeta_servlet;
import com.zeta_servlet.Utils.Criptografia;
import com.zeta_servlet.Utils.Filtro;
import com.zeta_servlet.Utils.Regex;
import com.zeta_servlet.daos.*;
import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import com.zeta_servlet.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TESTE DE CONEXÃO
//        try {
//            Conexao dbConn = new Conexao();
//            if (dbConn.conectar() == null) {
//                System.out.println("Erro de conexão");
//            }
//            else {
//                System.out.println("Conexão bem sucedida");
//            }
//            System.out.println("Saindo do db! \uD83D\uDC49\uD83D\uDC48\uD83D\uDE16");
//            dbConn.desconectar(dbConn.conectar());
//            }
//            catch (Exception e) {
//                ExceptionHandler eh = new ExceptionHandler(e);
//                eh.printExeption();
//            }
        //// TESTE ADMIN
//        AdmDAO admDao = new AdmDAO();
//        Adm admTOP = new Adm("joaoSouzera00@germinare.org.br",1,"admtop");
//        Adm adm1 = new Adm("raphaely.sales@germinare.org.br", 2, "e2510");

//        if (admDao.updateSenha(adm1, "e2510") == 1){
//            System.out.println("Funcionou");
//        }
//        else{
//            System.out.println("Não funcionou");
//        }

//        if (adm.inserir(adm1) == 1){
//            System.out.println("Administrador cadrastado com sucesso!");
//        }
//        else{
//            System.out.println("Não foi possível inserir o administrador...");
//        }
//        if(adm.remover(2)){
//            System.out.println("Funcionou");
//        }
//        else{
//            System.out.println("erro");
//        }
//        System.out.println(admDao.buscar()); // buscar por email

//        System.out.println(admDao.buscarId(1));


        // TESTES FORNECEDOR



//    FornecedorDAO fornecedorDAO = new FornecedorDAO();
//    System.out.println(fornecedorDAO.buscar());
        try {
//            int id=1;
//            List<Adm> liA = new ArrayList<>();
//            liA=admDAO.buscarId(id);
//            if (admDAO.updateSenha(liA.get(0), "Senha")>0){
//                System.out.println("Funcionou");
//            }
//            else{
//                System.out.println("Erro");
//            }
//            System.out.println(admDAO.buscar()  );


//            AssinaturaDAO assinaturaDAO = new AssinaturaDAO();
//            System.out.println(assinaturaDAO.buscar());

//            Regex regex = new Regex();
//            String cpnj = "62.173.620/0001-80";
//            String cnpjFormat = regex.formatarCnpj(cpnj);
//
//            System.out.println(cnpjFormat);
//            System.out.println(regex.formatarCnpj(cnpjFormat));

//            AulaDAO aulaDAO = new AulaDAO();
//            Filtro filtro = new Filtro();
//            System.out.println((aulaDAO.buscarPorId(39)));

//            Flash_cardDAO flashCardDAO = new Flash_cardDAO();
//            Flash_card flash = new Flash_card(0,"teste","teste",35);
//            flashCardDAO.inserir(flash);

//            AulaDAO aulaDAO = new AulaDAO();
//            List<Aula> liAu;
//            liAu=aulaDAO.buscar();
//            int i = 35;
//            System.out.println(i);
//            Aula au = liAu.get(i);
//            System.out.println(au);
            FlashCardDAO flashCardDAO = new FlashCardDAO();
            FlashCard flashCard = new FlashCard(45, "teste", "teste", 8);
            flashCardDAO.updateFrente(flashCard);
            flashCardDAO.updateVerso(flashCard);
            flashCardDAO.updateIdAula(flashCard);
            System.out.println(flashCardDAO.buscarPorId(45));



//            Criptografia criptografia = new Criptografia("billie2510e");
//            String mensagem = "Nicolaaaaaas25";
//            String crip = criptografia.criptografar(mensagem);
//            System.out.println(crip);
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
            e.printStackTrace();
        }
    }//main
}//class