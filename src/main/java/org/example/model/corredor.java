package org.example.model;

import org.example.connection.connectionUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class corredor{
    private int id;
    private String nombre;
    private String login;
    private int acciones_disponibles;
    boolean flag = false;
    private final String INSERTCOMPRA = "INSERT INTO cliente_empresa (codigo_cliente, codigo_empresa, numero_acciones) VALUES (?,?,?)";
    private final String COMPRUEBA = "SELECT acciones_disponibles FROM empresa WHERE codigo=?";
    private final String RESTA = "UPDATE empresa SET acciones_disponibles=? WHERE codigo=?";

    public corredor(int id, String nombre, String login) {
        this.id = id;
        this.nombre = nombre;
        this.login = login;
    }

    public corredor() {
    }

    public boolean compruebaAcciones(int id_empresa, int n_acciones){
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

            if(acciones_disponibles > n_acciones){
                result = true;
            }

        } catch (SQLException ex) {

        }
        return result;
    }

    public synchronized boolean comprar(int n_acciones, int id_cliente, int id_empresa, int opcion){
        boolean result = false;
        while(flag){
                try{
                    wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        flag = true;
            if(compruebaAcciones(id_empresa,n_acciones)){
                try {
                    java.sql.Connection conn = connectionUtils.getConnection();
                    PreparedStatement ps = conn.prepareStatement(INSERTCOMPRA);
                    ps.setInt(1, id_cliente);
                    ps.setInt(2, id_empresa);
                    ps.setInt(3, n_acciones);
                    int rs = ps.executeUpdate();
                    if(rs > 0){
                        if(restaAcciones(id_empresa, n_acciones)){
                            result = true;
                            System.out.println("Compra realizada con exito");
                        }else{
                            System.out.println("No se han podido restar las acciones");
                        }
                        flag = false;
                        notifyAll();
                    }

                } catch (SQLException ex) {
                }
            }else{
                if(opcion == 2){
                    if(acciones_disponibles != 0){
                        System.out.println("El numero de acciones que se desea comprar" +
                                " es superior a las disponibles, vamos a comprar todas las posibles");
                        try {
                            java.sql.Connection conn = connectionUtils.getConnection();
                            PreparedStatement ps = conn.prepareStatement(INSERTCOMPRA);
                            ps.setInt(1, id_cliente);
                            ps.setInt(2, id_empresa);
                            ps.setInt(3, acciones_disponibles);
                            int rs = ps.executeUpdate();
                            if(rs > 0){
                                System.out.println("Voy a comprar: "+ acciones_disponibles);
                                if(restaAcciones(id_empresa, acciones_disponibles)){
                                    result = true;
                                    System.out.println("Compra realizada con exito");
                                }else{
                                    System.out.println("No se han podido restar las acciones");
                                }
                                flag = false;
                                notifyAll();
                            }

                        } catch (SQLException ex) {
                        }
                    }else{
                        System.out.println("La empresa no dispone de acciones en este instante, lo sentimos.");
                        flag = false;
                        notifyAll();
                    }
                }else{
                    flag = false;
                    notifyAll();
                    System.out.println("El numero de acciones disponibles es menor al que desea comprar");
                }
            }
            return result;
        }

        public boolean restaAcciones(int id_empresa, int n_acciones){
            boolean result = false;
            try {
                java.sql.Connection conn = connectionUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(RESTA);
                ps.setInt(1, (acciones_disponibles - n_acciones));
                ps.setInt(2, id_empresa);
                int rs = ps.executeUpdate();
                if(rs > 0){
                    result = true;
                }

            } catch (SQLException ex) {
            }
            return result;
        }
    }
