package org.example.querys;

import org.example.connection.connectionUtils;
import org.example.model.client;
import org.example.model.enterprise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class querys {

    final static String SELECTALLCLIENTS = "SELECT * FROM cliente";
    final static String SELECTALLENTRERPRISE = "SELECt * FROM empresa";

    public static List<client> selectAllClient() {
        List<client> aux = new ArrayList<>();
        client cli;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLCLIENTS);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                cli = new client(s.getString("nombre"),s.getString("dni"),s.getString("apellidos"),s.getString("email"),s.getString("telefono"), s.getTimestamp("fecha_nac"));
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
                enterprise = new enterprise(s.getString("nombre"), s.getInt("acciones_disponibles"));
                aux.add(enterprise);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

}
