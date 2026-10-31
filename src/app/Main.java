package app;

import app.data.EmployeeReader;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("here is the main method");

        EmployeeReader employeeReader = new EmployeeReader();
        //checking if it prints
        System.out.println(employeeReader.load("Hospital_Applicants_Form.txt"));
    }
}