package org.example.gui;

import org.example.model.client;
import org.example.model.enterprise;
import org.example.querys.querys;
import org.example.threads.*;
import org.example.utils.utilities;

import java.util.List;

public class menu {

    public static void info(){
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
        thread1 t1 = new thread1();
        thread2 t2 = new thread2();
        thread3 t3 = new thread3();
        thread4 t4 = new thread4();
        thread5 t5 = new thread5();
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Inserte una opción válida");
                    break;
            }

        }while (option!=6);
    }

}
