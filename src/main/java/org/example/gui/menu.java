package org.example.gui;

import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;
import org.example.querys.querys;
import org.example.threads.*;
import org.example.utils.utilities;
import java.util.List;

public class menu {

    public static void info(){
        querys.borrarCompras();
        System.out.println("Mostrando los clientes...");
        List<client> clients = querys.selectAllClient();
        for(client c : clients){
            System.out.println(c);
        }
        System.out.println("Mostrando las empresas...");
        List<enterprise> enterprises = querys.selectAllEnterprise();
        for (enterprise e : enterprises){
            System.out.println(e);
        }
        mainMenu();
    }

    public static void mainMenu() {
        int option = 0;
        do {
            System.out.println("Bienvenido a la bolsa");
            System.out.println("1.- Acceso de dos corredores a la vez para la compra de acciones de dos" +
                    " empresas diferentes por parte de dos clientes distintos");
            System.out.println("2.- Acceso de dos corredores a la vez para la compra de accionesde una" +
                    " misma empresa  por parte de dos clientes distintos");
            System.out.println("3.- Alta por parte de un corredor de una nueva empresa. A su vez,otro" +
                    " corredor intenta eliminar una empresa y de manera " +
                    "simultánea un tercer corredor intenta comprar acciones de la empresa que va a ser eliminada.");
            System.out.println("4.- Acceso de 3 operadores de cuenta para comprar por parte de 3 clientes distintos" +
                    " acciones de la empresa clave. El primercliente querrá comprar 70 acciones, el segundo 80" +
                    " y el tercero 100 respectivamente.");
            System.out.println("5.- Resumen de todas las empresas de la sucursal indicando susacciones disponibles." +
                    " Resumen de todos los clientes indicandolas acciones que tienen en cada empresa.");
            System.out.println("6.- Salir");
            option = utilities.getInt();

            switch (option){
                case 1:
                    System.out.println("Entrando con el usuario miguel...");
                    if(querys.login("migue@example.com", "1234") != null){
                        System.out.println("Usuario ha entrado con exito");

                        List<enterprise> empresas = querys.selectAllEnterprise();
                        enterprise empresa = empresas.get((int) (Math.random() * empresas.size()));
                        System.out.println("Vamos a comprar a la empresa: " + empresa);

                        List<client> clientes = querys.selectAllClient();
                        client cliente = clientes.get((int) (Math.random()* (clientes.size()-1)));
                        System.out.println("Comprando con el cliente: " + cliente.getName());


                        int n_acciones = (int) (Math.random() * 50);
                        if(n_acciones == 0){
                            n_acciones = 1;
                        }
                        System.out.println("Se va a realizar la compra de " + n_acciones + " acciones");

                        corredor corredor = new corredor();

                        thread1 thread1 = new thread1(corredor, cliente, empresa, n_acciones, option);

                        empresas = querys.selectAllEnterprise();
                        empresa = empresas.get((int) (Math.random() * empresas.size()));
                        System.out.println("Vamos a comprar a la empresa: " + empresa);

                        cliente = clientes.get((int) (Math.random()* (clientes.size()-1)));
                        System.out.println("Comprando con el cliente: " + cliente.getName());

                        n_acciones = (int) (Math.random() * 50);
                        if(n_acciones == 0){
                            n_acciones = 1;
                        }
                        System.out.println("Se va a realizar la compra de " + n_acciones + " acciones");

                        thread1 thread1_2 = new thread1(corredor, cliente, empresa, n_acciones,option);

                        thread1.start();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        thread1_2.start();
                    }else{
                        System.out.println("Ha ocurrido un error");
                    }
                    break;
                case 2:
                    System.out.println("Entrando con el usuario miguel...");
                    if(querys.login("migue@example.com", "1234") != null){
                        System.out.println("Usuario ha entrado con exito");

                        List<enterprise> empresas = querys.selectAllEnterprise();
                        enterprise empresa = empresas.get(empresas.size() - 1);
                        System.out.println("Los dos clientes van a comprar a la empresa: " + empresa);

                        List<client> clientes = querys.selectAllClient();
                        client cliente = clientes.get((int) (Math.random()* (clientes.size()-1)));
                        System.out.println("Comprando con el cliente: " + cliente.getName());


                        int n_acciones = (int) (Math.random() * 50);
                        if(n_acciones == 0){
                            n_acciones = 1;
                        }
                        System.out.println("Se va a realizar la compra de " + n_acciones + " acciones");

                        corredor corredor = new corredor();

                        thread1 thread1 = new thread1(corredor, cliente, empresa, n_acciones, option);

                        cliente = clientes.get((int) (Math.random()* (clientes.size()-1)));
                        System.out.println("Comprando con el cliente: " + cliente.getName());

                        n_acciones = (int) (Math.random() * 50);
                        if(n_acciones == 0){
                            n_acciones = 1;
                        }
                        System.out.println("Se va a realizar la compra de " + n_acciones + " acciones");

                        thread1 thread1_2 = new thread1(corredor, cliente, empresa, n_acciones,option);

                        thread1.start();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        thread1_2.start();
                    }else{
                        System.out.println("Ha ocurrido un error");
                    }
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Inserte una opción válida");
                    break;
            }

        }while (option!=6);
    }

}
