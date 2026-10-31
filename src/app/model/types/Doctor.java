package app.model.types;

import app.model.Employee;
import app.model.Department;
import app.model.ManagerType;

public class Doctor extends Employee {

    public Doctor(String name, Department department, ManagerType managerType) {
        super(name, department, managerType);
    }

    @Override
    public String getProfession() {
        return "Doctor";
    }

}
