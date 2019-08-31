/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.topicosespecias;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class ListaItemsMenu extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void getListOfItens(HttpServletRequest request, HttpServletResponse response,String query)
            throws ServletException, IOException {
        List<Item> lista = new ArrayList<>();
        try {
            //Registering the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Getting the Connection object
            String URL = "jdbc:derby://localhost:1527/ecommerce;user=univali;password=univali";
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lista.add(new Item(rs.getInt("Id"),rs.getString("nome"),rs.getString("descricao"),rs.getString("detalhes")));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = gson.toJsonTree(lista).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("itens", jsonArray);
        try (PrintWriter out = response.getWriter()) {
            out.println(jsonObject);
        }
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
        String query = "SELECT Id,nome,descricao,detalhes FROM ITEM";
        getListOfItens(request, response, query);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("descricao");
        String query = "SELECT Id,nome,descricao,detalhes FROM ITEM WHERE descricao LIKE '%" + description  +"%'";        
        getListOfItens(req, resp, query);
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
