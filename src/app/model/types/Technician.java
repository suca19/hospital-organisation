package app.model.types;

import app.model.Employee;
import app.model.department.HospitalDepartment;
import app.model.position.Position;

// A technician: an Employee whose profession is "Technician"
public class Technician extends Employee {

    public Technician(String name, HospitalDepartment department, Position managerType) {
        super(name, department, managerType);
    }

    @Override
    public String getProfession() {
        return "Technician";
    }
}
