/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.topicosespecias;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Fabio
 */
public class Purchase extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        JSONObject jsonObject = new JSONObject(json);
        User user = gson.fromJson(jsonObject.get("user").toString(), User.class);
        JSONArray itens = ((JSONArray) ((JSONObject) jsonObject.get("itens")).get("itens"));
        for (int i = 0; i < itens.length(); i++) {
            insertItemPedido(getIdUser(user.getLogin()),(itens.getJSONObject(i)).getInt("Id"),itens.getJSONObject(i).getInt("quantidade"));
        }
    }

    private void insertItemPedido(int idUser, int idItem, int quantidade) {
        try {
            //Registering the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Getting the Connection object
            String URL = "jdbc:derby://localhost:1527/ecommerce;user=univali;password=univali";
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();

            stmt = conn.createStatement();
            stmt.execute("INSERT INTO ITEM_PEDIDO(ID_PEDIDO,ID_ITEM,QUANTIDADE) VALUES (" + idUser + "," + idItem + "," + quantidade + ")");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getIdUser(String login) {
        int id = 0;
        try {
            //Registering the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Getting the Connection object
            String URL = "jdbc:derby://localhost:1527/ecommerce;user=univali;password=univali";
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Id FROM USUARIO WHERE LOGIN='" + login + "'");
            while (rs.next()) {
                id = rs.getInt("Id");
            }
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    
}
