package org.example.threads;

import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;

public class thread1 extends Thread{

    private corredor corredor;
    private client cliente;
    private enterprise empresa;
    private int n_acciones;
    private int opcion;

    public thread1 (corredor corredor, client cliente, enterprise empresa, int n_acciones, int opcion){
        this.corredor = corredor;
        this.cliente = cliente;
        this.empresa = empresa;
        this.n_acciones = n_acciones;
        this.opcion = opcion;
    }

    @Override
    public void run() {
        this.corredor.comprar(this.n_acciones, cliente.getId(), empresa.getId(), opcion);
    }
}
