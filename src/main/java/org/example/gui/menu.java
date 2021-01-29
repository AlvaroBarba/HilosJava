package org.example.gui;

import org.example.model.client;
import org.example.model.corredor;
import org.example.model.enterprise;
import org.example.querys.querys;
import org.example.threads.thread1_2;
import org.example.threads.thread3;
import org.example.threads.thread4;
import org.example.utils.utilities;

import java.util.List;

public class menu {

    public static void info() {
        querys querys = new querys();
        querys.borrarCompras();
        System.out.println("Mostrando los clientes...");
        List<client> clients = querys.selectAllClient();
        for (client c : clients) {
            System.out.println(c);
        }
        System.out.println("Mostrando las empresas...");
        List<enterprise> enterprises = querys.selectAllEnterprise();
        for (enterprise e : enterprises) {
            System.out.println(e);
        }
        mainMenu(enterprises);
    }

    public static void mainMenu(List<enterprise> enterprises) {
        querys querys = new querys();
        corredor corredor;
        corredor corredor2;
        enterprise enterprise;
        enterprise enterprise2;
        client cliente;
        client cliente2;
        List<client> clientes;
        int n_actions;
        int n_action2;
        thread1_2 thread1;
        thread1_2 thread1_2;
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

            switch (option) {
                case 1:
                    //corredor 1, cliente 1, empresa 1 y numero de acciones
                    corredor = new corredor("migue@example.com", "1234");
                    enterprise = enterprises.get((int) (Math.random() * enterprises.size()));
                    clientes = querys.selectAllClient();
                    cliente = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    int n_acciones = (int) (Math.random() * 50);
                    if (n_acciones == 0) {
                        n_acciones = 1;
                    }
                    System.out.println("Se van a comprar " + n_acciones + " acciones en la empresa " + enterprise.getName() + " con el cliente " + cliente.getName());

                    //corredor 2, cliente 2, empresa 2 y numero de acciones
                    corredor2 = new corredor("alvaro@example.com", "1234");
                    enterprise2 = enterprises.get((int) (Math.random() * enterprises.size()));
                    cliente2 = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    int n_acciones2 = (int) (Math.random() * 50);
                    if (n_acciones2 == 0) {
                        n_acciones2 = 1;
                    }
                    System.out.println("Se van a comprar " + n_acciones2 + " acciones en la empresa " + enterprise2.getName() + " con el cliente " + cliente2.getName());

                    System.out.println("A la espera de que los corredores se logueen...");

                    //Creacion de hilos
                    thread1 = new thread1_2(corredor, cliente, enterprise, n_acciones, option);
                    thread1_2 = new thread1_2(corredor2, cliente2, enterprise2, n_acciones2, option);

                    //Ejecucion hilos
                    thread1.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread1_2.start();
                    break;
                case 2:
                    //corredor 1, cliente 1 y numero de acciones
                    corredor = new corredor("migue@example.com", "1234");
                    clientes = querys.selectAllClient();
                    cliente = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    n_acciones = (int) (Math.random() * 50);
                    if (n_acciones == 0) {
                        n_acciones = 1;
                    }

                    //corredor 2, cliente 2 y numero de acciones
                    corredor2 = new corredor("alvaro@example.com", "1234");
                    cliente2 = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    n_acciones2 = (int) (Math.random() * 50);
                    if (n_acciones2 == 0) {
                        n_acciones2 = 1;
                    }

                    //EMPRESA
                    enterprise = enterprises.get((int) (Math.random() * enterprises.size()));
                    System.out.println("Se van a comprar " + n_acciones + " acciones en la empresa " + enterprise.getName() + " con el cliente " + cliente.getName());
                    System.out.println("Se van a comprar " + n_acciones2 + " acciones en la empresa " + enterprise.getName() + " con el cliente " + cliente2.getName());

                    System.out.println("A la espera de que los corredores se logueen...");

                    //Creacion de hilos
                    thread1 = new thread1_2(corredor, cliente, enterprise, n_acciones, option);
                    thread1_2 = new thread1_2(corredor2, cliente2, enterprise, n_acciones2, option);

                    //Ejecucion hilos
                    thread1.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread1_2.start();
                    break;
                case 3:
                    clientes = querys.selectAllClient();
                    cliente = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    enterprises = querys.selectAllEnterprise();
                    enterprise = enterprises.get((int) (Math.random() * (enterprises.size() - 1)));
                    List<corredor> corredores = querys.selectAllCorredores();
                    thread3 thread3_1 = new thread3(corredores.get(0), 1);
                    thread3 thread3_2 = new thread3(corredores.get(corredores.size() - 2), enterprise, 2);
                    thread3 thread3_3 = new thread3(corredores.get(corredores.size() - 1), cliente, enterprise, 25, 3);

                    thread3_1.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread3_2.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread3_3.start();
                    break;
                case 4:
                    clientes = querys.selectAllClient();
                    cliente = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    enterprise = querys.getEnterpriseByName("Clave");
                    cliente = clientes.get((int) (Math.random() * (clientes.size() - 1)));
                    corredores = querys.selectAllCorredores();
                    thread4 thread4_1 = new thread4(corredores.get(0), cliente, enterprise, 1);
                    thread4 thread4_2 = new thread4(corredores.get(corredores.size() - 2), cliente, enterprise, 2);
                    thread4 thread4_3 = new thread4(corredores.get(corredores.size() - 1), cliente, enterprise, 3);

                    thread4_1.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread4_2.start();
                    thread4_3.start();
                    break;
                case 5:
                    System.out.println("Mostrando Resumen...");
                    System.out.println("---Empresas---");
                    enterprises = querys.selectAllEnterprise();
                    for (enterprise e : enterprises) {
                        System.out.println(e);
                    }
                    System.out.println("---Acciones compradas de cada usuario---");
                    List<client> clients = querys.selectAllActions();
                    for (client c : clients) {
                        System.out.println(c);
                    }
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Inserte una opción válida");
                    break;
            }

        } while (option != 6);
    }

}
