package app.model.types;

import app.model.Employee;
import app.model.department.HospitalDepartment;
import app.model.position.Position;

// A doctor: an Employee whose profession is "Doctor"
public class Doctor extends Employee {

    public Doctor(String name, HospitalDepartment department, Position managerType) {
        super(name, department, managerType);
    }

    @Override
    public String getProfession() {
        return "Doctor";
    }

}
