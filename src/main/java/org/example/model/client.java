package org.example.model;

import java.sql.Timestamp;

public class client {

    private int id;
    private String name;
    private String dni;
    private String lastName;
    private String email;
    private String phone;
    private Timestamp dateOfBirth;
    private int n_actions;
    private String enterprisepurchaseactions;
    private String date_purchase;

    public client(int id, String name, String dni, String lastName, String email, String phone, Timestamp dateOfBirth, int n_actions, String enterprisepurchaseactions, String date_purchase) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.n_actions = n_actions;
        this.enterprisepurchaseactions = enterprisepurchaseactions;
        this.date_purchase = date_purchase;
    }

    public client(int id, String name, String dni, String lastName, String email, String phone, Timestamp dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public String getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(String date_purchase) {
        this.date_purchase = date_purchase;
    }

    public String getEnterprisepurchaseactions() {
        return enterprisepurchaseactions;
    }

    public void setEnterprisepurchaseactions(String enterprisepurchaseactions) {
        this.enterprisepurchaseactions = enterprisepurchaseactions;
    }

    public int getN_actions() {
        return n_actions;
    }

    public void setN_actions(int n_actions) {
        this.n_actions = n_actions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String text;
        if (n_actions != 0 && enterprisepurchaseactions != null) {
            text = "cliente{" +
                    "nombre='" + name + '\'' +
                    ", dni='" + dni + '\'' +
                    ", numero de acciones=" + n_actions + '\'' +
                    ", compradas en la empresa=" + enterprisepurchaseactions +
                    '}';
        } else {
            text = "cliente{" +
                    "nombre='" + name + '\'' +
                    ", dni='" + dni + '\'' +
                    ", apellidos='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", telefono='" + phone + '\'' +
                    ", fecha de nacimiento=" + dateOfBirth +
                    '}';
        }
        return text;
    }
}
