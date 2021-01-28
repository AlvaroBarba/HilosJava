package org.example.model;

public class corredor {
    private int id;
    private String nombre;
    private String login;
    private String pass;

    public corredor(int id, String nombre, String login) {
        this.id = id;
        this.nombre = nombre;
        this.login = login;
    }

    public corredor(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public corredor() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
