package org.example.threads;

import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;
import org.example.querys.querys;

public class thread3 extends Thread {
    private final corredor corredor;
    private final querys query = new querys();
    private final int option;
    private client cliente;
    private enterprise empresa;
    private int n_acciones;

    public thread3(corredor corredor, client cliente, enterprise empresa, int n_acciones, int option) {
        this.corredor = corredor;
        this.cliente = cliente;
        this.empresa = empresa;
        this.n_acciones = n_acciones;
        this.option = option;
    }

    public thread3(corredor corredor, int option) {
        this.corredor = corredor;
        this.option = option;
    }

    public thread3(corredor corredor, enterprise enterprise, int option) {
        this.corredor = corredor;
        this.empresa = enterprise;
        this.option = option;
    }


    @Override
    public void run() {
        System.out.println("LA OPCION AMBARAJAMDA ES" + option);
        if (option == 1) {
            this.query.login(corredor.getLogin(), corredor.getPass());
            this.query.createEnterprise(new enterprise("Restaurante Paco Mer", 5000));
        } else if (option == 2) {
            this.query.login(corredor.getLogin(), corredor.getPass());
            this.query.borraEmpresa(this.empresa.getName());
        } else {
            this.query.login(corredor.getLogin(), corredor.getPass());
            this.query.comprar(30, cliente.getId(), empresa.getId(), 3);
        }
    }
}
