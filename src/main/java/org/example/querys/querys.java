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
    final static String SELECTALLCORREDORES = "SELECt * FROM corredor";
    final static String CREATEENTERPRISE = "INSERT INTO empresa (nombre, acciones_disponibles) VALUES (?,?)";
    final static String SELECTALLCLIENTS = "SELECT * FROM cliente";
    final static String SELECTALLENTRERPRISE = "SELECT * FROM empresa";
    final static String INSERTCOMPRA = "INSERT INTO cliente_empresa (codigo_cliente, codigo_empresa, numero_acciones) VALUES (?,?,?)";
    final static String LOGIN = "SELECT * FROM corredor WHERE login=? AND password=?";
    final static String BORRAR = "DELETE FROM cliente_empresa";
    final static String COMPRUEBA = "SELECT acciones_disponibles FROM empresa WHERE codigo=?";
    final static String RESTA = "UPDATE empresa SET acciones_disponibles=? WHERE codigo=?";
    final static String DELETEENTERPRISE = "DELETE FROM empresa WHERE nombre=?";
    final static String GETENTERPRISE = "SELECT * FROM empresa WHERE codigo=?";
    final static String GETENTERPRISEBYNAME = "SELECT * FROM empresa WHERE nombre=?";
    final static String SELECTACTIONS = "select * from cliente INNER JOIN cliente_empresa ON cliente_empresa.codigo_cliente = cliente.codigo INNER JOIN empresa ON cliente_empresa.codigo_empresa = empresa.codigo";
    private int acciones_disponibles;
    private boolean flag = false;

    public synchronized boolean compruebaAcciones(int id_empresa, int n_acciones) {
        boolean result = false;
        acciones_disponibles = -1;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(COMPRUEBA);
            ps.setInt(1, id_empresa);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                acciones_disponibles = s.getInt("acciones_disponibles");
            }
            if (s != null) {
                s.close();
            }

            if (acciones_disponibles > n_acciones) {
                result = true;
            }

        } catch (SQLException ex) {

        }
        return result;
    }

    public synchronized boolean comprar(int n_acciones, int id_cliente, int id_empresa, int opcion) {
        boolean result = false;

        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        if (compruebaAcciones(id_empresa, n_acciones)) {
            try {
                java.sql.Connection conn = connectionUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERTCOMPRA);
                ps.setInt(1, id_cliente);
                ps.setInt(2, id_empresa);
                ps.setInt(3, n_acciones);
                int rs = ps.executeUpdate();
                if (rs > 0) {
                    if (restaAcciones(id_empresa, n_acciones)) {
                        result = true;
                        System.out.println("Compra realizada con exito");
                    } else {
                        System.out.println("No se han podido restar las acciones");
                    }
                    flag = false;
                    notifyAll();
                }

            } catch (SQLException ex) {
            }
        } else {
            if (opcion == 2) {
                if (acciones_disponibles != 0) {
                    System.out.println("El numero de acciones que se desea comprar" +
                            " es superior a las disponibles, vamos a comprar todas las posibles");
                    try {
                        java.sql.Connection conn = connectionUtils.getConnection();
                        PreparedStatement ps = conn.prepareStatement(INSERTCOMPRA);
                        ps.setInt(1, id_cliente);
                        ps.setInt(2, id_empresa);
                        ps.setInt(3, acciones_disponibles);
                        int rs = ps.executeUpdate();
                        if (rs > 0) {
                            System.out.println("Voy a comprar: " + acciones_disponibles);
                            if (restaAcciones(id_empresa, acciones_disponibles)) {
                                result = true;
                                System.out.println("Compra realizada con exito");
                            } else {
                                System.out.println("No se han podido restar las acciones");
                            }
                            flag = false;
                            notifyAll();
                        }

                    } catch (SQLException ex) {
                    }
                } else {
                    System.out.println("La empresa no dispone de acciones en este instante, lo sentimos.");
                    flag = false;
                    notifyAll();
                }
            } else if (getEnterprise(id_empresa) == null) {
                System.out.println("La empresa elegida no existe, pruebe con otra empresa de la siguiente lista");
                System.out.println("---Lista de Empresas---");
                List<enterprise> enterprises = selectAllEnterprise();
                for (enterprise e : enterprises) {
                    System.out.println(e);
                }
            } else {
                flag = false;
                notifyAll();
                System.out.println("El numero de acciones disponibles es menor al que desea comprar");
            }
        }
        return result;
    }

    public synchronized boolean restaAcciones(int id_empresa, int n_acciones) {
        flag = true;
        boolean result = false;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(RESTA);
            ps.setInt(1, (acciones_disponibles - n_acciones));
            ps.setInt(2, id_empresa);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
                flag = false;
                notifyAll();
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    public synchronized boolean borraEmpresa(String name) {
        boolean flag = false;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETEENTERPRISE);
            ps.setString(1, name);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                flag = true;
                System.out.println("Empresa " + name + " borrada con éxito");
            }

        } catch (SQLException e) {
            System.out.println(e);

        }
        return flag;

    }

    public synchronized enterprise getEnterprise(int id) {
        enterprise enterprise = null;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(GETENTERPRISE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                enterprise = new enterprise(rs.getString("nombre"), rs.getInt("acciones_disponibles"));
            }

        } catch (SQLException e) {

        }
        return enterprise;
    }

    public synchronized enterprise getEnterpriseByName(String name) {
        enterprise enterprise = null;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(GETENTERPRISEBYNAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                enterprise = new enterprise(rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("acciones_disponibles"));
            }

        } catch (SQLException e) {

        }
        return enterprise;
    }


    public synchronized corredor login(String login, String pass) {
        corredor corredor = null;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(LOGIN);
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                corredor = new corredor(s.getInt("codigo"), s.getString("nombre"), s.getString("login"));
                System.out.println("Corredor " + corredor.getNombre() + " ha entrado con exito");
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return corredor;
    }

    public synchronized List<client> selectAllClient() {
        List<client> aux = new ArrayList<>();
        client cli;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLCLIENTS);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                cli = new client(s.getInt("codigo"), s.getString("nombre"), s.getString("dni"), s.getString("apellidos"), s.getString("email"), s.getString("telefono"), s.getTimestamp("fecha_nac"));
                aux.add(cli);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public synchronized List<corredor> selectAllCorredores() {
        List<corredor> aux = new ArrayList<>();
        corredor corredor;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLCORREDORES);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                corredor = new corredor(s.getString("login"), s.getString("password"));
                aux.add(corredor);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }


    public synchronized List<enterprise> selectAllEnterprise() {
        List<enterprise> aux = new ArrayList<>();
        enterprise enterprise;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTALLENTRERPRISE);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                enterprise = new enterprise(s.getInt("codigo"), s.getString("nombre"), s.getInt("acciones_disponibles"));
                aux.add(enterprise);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

    public synchronized void borrarCompras() {
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(BORRAR);
            ps.executeUpdate();

        } catch (SQLException ex) {

        }
    }


    public synchronized boolean createEnterprise(enterprise e) {
        boolean flag = false;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATEENTERPRISE);
            ps.setString(1, e.getName());
            ps.setInt(2, e.getN_actions());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                flag = true;
                System.out.println("Empresa " + e.getName() + " creada con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return flag;
    }

    public synchronized List<client> selectAllActions() {
        List<client> aux = new ArrayList<>();
        client client;
        try {
            java.sql.Connection conn = connectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECTACTIONS);
            ResultSet s = ps.executeQuery();

            while (s != null && s.next()) {
                client = new client(s.getInt("cliente.codigo"), s.getString("cliente.nombre"), s.getString("dni"), s.getString("apellidos"), s.getString("email"), s.getString("telefono"), s.getTimestamp("fecha_nac"), s.getInt("numero_acciones"), s.getString("empresa.nombre"), s.getString("fecha_hora_compra"));
                aux.add(client);
            }
            if (s != null) {
                s.close();
            }

        } catch (SQLException ex) {

        }
        return aux;
    }

}
