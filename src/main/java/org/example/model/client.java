package org.example.model;

import java.sql.Timestamp;

public class client {

    private String name;
    private String dni;
    private String lastName;
    private String email;
    private String phone;
    private Timestamp dateOfBirth;

    public client(String name, String dni, String lastName, String email, String phone, Timestamp dateOfBirth) {
        this.name = name;
        this.dni = dni;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return "cliente{" +
                "nombre='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", apellidos='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + phone + '\'' +
                ", fecha de nacimiento=" + dateOfBirth +
                '}';
    }
}
