package org.example.querys;

import org.example.connection.connectionUtils;
import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class querys {

    final static String SELECTALLCLIENTS = "SELECT * FROM cliente";
    final static String SELECTALLENTRERPRISE = "SELECt * FROM empresa";
    final static String LOGIN = "SELECT * FROM corredor WHERE login=? AND password=?";
    final static String BORRAR = "DELETE FROM cliente_empresa";

    public static corredor login(String login, String pass){
        corredor corredor = null;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(LOGIN);
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                corredor = new corredor(s.getInt("codigo"), s.getString("nombre"), s.getString("login"));
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return corredor;
    }

    public static List<client> selectAllClient() {
        List<client> aux = new ArrayList<>();
        client cli;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLCLIENTS);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                cli = new client(s.getInt("codigo"),s.getString("nombre"),s.getString("dni"),s.getString("apellidos"),s.getString("email"),s.getString("telefono"), s.getTimestamp("fecha_nac"));
                aux.add(cli);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static List<enterprise> selectAllEnterprise() {
        List<enterprise> aux = new ArrayList<>();
        enterprise enterprise;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLENTRERPRISE);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                enterprise = new enterprise(s.getInt("codigo"),s.getString("nombre"), s.getInt("acciones_disponibles"));
                aux.add(enterprise);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public static void borrarCompras(){
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(BORRAR);
            ps.executeUpdate();

        } catch (SQLException ex) {

        }
    }

}
