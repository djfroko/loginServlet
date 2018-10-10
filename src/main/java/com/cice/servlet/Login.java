package com.cice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class Login extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String pass = req.getParameter("pass");

        try {
            Class.forName("com.cice.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/login", "root", "root");
            Statement statement = connection.createStatement();
            String sql = " SELECT * FROM USUARIOS where user='"+user+" ' and pass='"+pass+ "'" ;
            ResultSet resultado = statement.executeQuery(sql);

            if (resultado.first()) {

            resp.getWriter().print("Usuario Logueado correctametne");
        }else{
                resp.getWriter().print("Usuario o contraseña no coinciden");
            }

            resultado.close();
            statement.close();
            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
