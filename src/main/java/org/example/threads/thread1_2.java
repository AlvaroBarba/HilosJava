package org.example.threads;

import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;
import org.example.querys.querys;

public class thread1_2 extends Thread {
    private final querys querys = new querys();
    private final corredor corredor;
    private final client cliente;
    private final enterprise empresa;
    private final int n_acciones;
    private final int opcion;

    public thread1_2(corredor corredor, client cliente, enterprise empresa, int n_acciones, int opcion) {
        this.corredor = corredor;
        this.cliente = cliente;
        this.empresa = empresa;
        this.n_acciones = n_acciones;
        this.opcion = opcion;
    }

    @Override
    public void run() {
        querys.login(corredor.getLogin(), corredor.getPass());
        querys.comprar(this.n_acciones, cliente.getId(), empresa.getId(), opcion);
    }
}
