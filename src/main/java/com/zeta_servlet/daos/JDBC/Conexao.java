package com.zeta_servlet.daos.JDBC;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao {

    public Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            Dotenv dotenv = Dotenv.configure().load();

            String url_db = dotenv.get("URL_DB");
            String user_db = dotenv.get("USER_DB");
            String password_db = dotenv.get("PASSWORD_DB");
            conn = DriverManager.getConnection(url_db, user_db, password_db);
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }finally {
            return conn;
        }
    }

    public void desconectar(Connection conn){
        try {


            if (conn != null &&  !conn.isClosed()){
                conn.close();}
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
    }
}
