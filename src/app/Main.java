package app;

import java.util.Scanner;

import app.data.EmployeeReader;

public class Main {
    public static void main(String[] args) {

        Scanner sc =  new Scanner(System.in);
        //int number = sc.nextInt();
        EmployeeReader employeeReader = new EmployeeReader();

        //Option option = Option.fromOptionNumber(number);


        
        System.out.println(employeeReader.load("Hospital_Applicants_Form.txt"));
    }
}