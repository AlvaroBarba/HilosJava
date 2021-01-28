package org.example.threads;

import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;
import org.example.querys.querys;

public class thread4 extends Thread {

    private int option;
    private querys query = new querys();
    private corredor corredor;
    private client client;
    private enterprise enterprise;

    public thread4(corredor corredor, client cliente, enterprise empresa, int option) {
        this.corredor = corredor;
        this.client = cliente;
        this.enterprise = empresa;
        this.option = option;
    }

    public thread4() {
    }


    @Override
    public void run() {
        if (option == 1) {
            this.query.login(corredor.getLogin(), corredor.getPass());
            this.query.comprar(70, this.client.getId(), this.enterprise.getId(), 4);
        } else if (option == 2) {
            this.query.login(corredor.getLogin(), corredor.getPass());
            this.query.comprar(80, this.client.getId(), this.enterprise.getId(), 4);
        } else {
            this.query.login(corredor.getLogin(), corredor.getPass());
            this.query.comprar(100, this.client.getId(), this.enterprise.getId(), 4);
        }
    }
}
