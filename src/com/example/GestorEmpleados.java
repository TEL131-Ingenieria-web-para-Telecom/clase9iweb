package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorEmpleados {

    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/hr";

    public boolean login() {
        System.out.print("Ingrese su username: ");
        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine();
        System.out.print("Ingrese su password: ");
        String lastName = sc.nextLine();


        String sql = "select * from employees where first_name = ? and last_name = ?";
        System.out.println(sql);
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);

            try (ResultSet resultSet0 = pstmt.executeQuery()) {
                return resultSet0.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Employee> listarEmpleados() {

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from employees");
            while (resultSet.next()) {
                Employee e = new Employee();
                e.setId(resultSet.getInt(1));
                e.setFirstName(resultSet.getString("first_name"));
                e.setLastName(resultSet.getString(3));
                listaEmpleados.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public ArrayList<Employee> buscarEmpleadorPorLetra(String letra) {

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        String sql = "select * from employees where lower(first_name) like ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + letra + "%");

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Employee e = new Employee();
                    e.setId(resultSet.getInt(1));
                    e.setFirstName(resultSet.getString("first_name"));
                    e.setLastName(resultSet.getString(3));
                    listaEmpleados.add(e);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public void crearEmpleado(Employee employee) {

        String sql = "insert into employees (first_name, last_name, email, " +
                "password, phone_number, hire_date, job_id, salary, commission_pct, " +
                "manager_id, department_id, enabled) VALUES (?,?,?,?,?,now(),?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, "prueba" + employee.getFirstName());
            pstmt.setString(4, "pass");
            pstmt.setString(5, "123");
            pstmt.setString(6, "IT_PROG");
            pstmt.setInt(7, 20);
            pstmt.setNull(8, Types.DECIMAL);
            pstmt.setInt(9, 100);
            pstmt.setInt(10, 80);
            pstmt.setInt(11, 1);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int crearEmpleadoDevolverIdCreado(Employee employee) {

        String sql = "insert into employees (first_name, last_name, email, " +
                "password, phone_number, hire_date, job_id, salary, commission_pct, " +
                "manager_id, department_id, enabled) VALUES (?,?,?,?,?,now(),?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, "prueba" + employee.getFirstName());
            pstmt.setString(4, "pass");
            pstmt.setString(5, "123");
            pstmt.setString(6, "IT_PROG");
            pstmt.setInt(7, 20);
            pstmt.setNull(8, Types.DECIMAL);
            pstmt.setInt(9, 100);
            pstmt.setInt(10, 80);
            pstmt.setInt(11, 1);

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }else{
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
