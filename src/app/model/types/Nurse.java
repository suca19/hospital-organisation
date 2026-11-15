package app.model.types;

import app.model.Employee;
import app.model.department.HospitalDepartment;
import app.model.position.Position;

// A nurse: an Employee whose profession is "Nurse"
public class Nurse extends Employee {

    public Nurse(String name, HospitalDepartment department, Position managerType) {
        super(name, department, managerType);
    }

    @Override
    public String getProfession() {
        return "Nurse";
    }
}
