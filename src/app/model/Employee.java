package model;

public abstract class Employee {
    private String name;
    private String department;
    private String position;
    
    public Employee(String name, String department, String position) {
        this.name = name;
        this.department = department;
        this.position = position;
    }

    public String getName(){
        return name;
    }

    public String getDepartment(){
        return department;
    }
    public String getPosition(){
        return position;
    }
}
