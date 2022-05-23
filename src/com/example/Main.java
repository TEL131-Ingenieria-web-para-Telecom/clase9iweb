package com.example;


import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Bienvenido a mi sistema");
        Scanner sc = new Scanner(System.in);

        GestorEmpleados gestorEmpleados = new GestorEmpleados();
        //if (gestorEmpleados.login()) {
        //    System.out.println("!!!!!! acceso concedido !!!!!!");
        //  ArrayList<Employee> listaEmpleados = gestorEmpleados.listarEmpleados();
/*
        System.out.print("Ingrese la letra a buscar: ");

        String letra = sc.nextLine();

        ArrayList<Employee> listaEmpleados = gestorEmpleados.buscarEmpleadorPorLetra(letra);

        for (Employee e : listaEmpleados) {
            System.out.println("Id: " + e.getId() + " | first_name: " + e.getFirstName());
        }*/
        /*} else {
            System.out.println("credenciales erroneas :(");
        }*/
        /*Job job = new Job();
        System.out.println("Ingrese los datos del trabajo");
        System.out.print("job id: ");
        job.setJobId(sc.nextLine());

        System.out.print("job title: ");
        job.setJobTitle(sc.nextLine());

        System.out.print("min salary: ");
        job.setMinSalary(Integer.parseInt(sc.nextLine()));

        System.out.print("max salary: ");
        job.setMaxSalary(Integer.parseInt(sc.nextLine()));

        GestorTrabajos gestorTrabajos = new GestorTrabajos();
        gestorTrabajos.insertarJob(job);
        System.out.println("trabajo creado exitosamente");*/
        Employee employee = new Employee();
        System.out.print("nombre: ");
        employee.setFirstName(sc.nextLine());
        System.out.print("apellido: ");
        employee.setLastName(sc.nextLine());

        GestorEmpleados ge = new GestorEmpleados();
        //ge.crearEmpleado(employee);
        int idCreado = ge.crearEmpleadoDevolverIdCreado(employee);
        System.out.println("idCreado: " + idCreado);
    }
}