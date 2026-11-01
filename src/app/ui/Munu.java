package app.ui;

import java.util.Scanner;

import app.data.EmployeeReader;
import java.util.List;
import app.model.Employee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Munu {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeReader employeeReader = new EmployeeReader();
    private List<Employee> employee;

    public void run() {

    }

    private List<Employee> loadFile(String filePath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {

        } catch (IOException e) {

        }
    }

    private int readInt() {
        return scanner.nextInt();
    }
}
