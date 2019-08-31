/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.topicosespecias;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabio
 */
public class Authentication extends HttpServlet {

    protected JsonObject hasAuthentication(String name, String password) {
        JsonObject responseUser = new JsonObject();
        try {
            //Registering the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Getting the Connection object
            String URL = "jdbc:derby://localhost:1527/ecommerce;user=univali;password=univali";
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM USUARIO";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("login").equals(name) && rs.getString("senha").equals(password)) {
                    JsonObject jsonUser = new JsonObject();
                    responseUser.addProperty("authorizated", Boolean.TRUE);
                    jsonUser.addProperty("login", rs.getString("LOGIN"));
                    jsonUser.addProperty("codigoSeguranca", rs.getString("CODIGO_SEGURANCA"));
                    jsonUser.addProperty("dataValidade", rs.getString("DATA_VALIDADE"));
                    jsonUser.addProperty("numeroCartao", rs.getString("NUMERO_CARTAO"));
                    responseUser.add("user", jsonUser);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("nome");
        String password = request.getParameter("senha");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(hasAuthentication(userName, password));
        out.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
