package app.model;

import app.model.department.DepartmentFactory;
import app.model.department.HospitalDepartment;
import app.model.position.Position;
import app.model.position.PositionFactory;
import app.model.types.Doctor;
import app.model.types.Nurse;
import app.model.types.Technician;

// The only class that knows the Employee subclasses: creates the right one for a profession
public class EmployeeFactory {
    // receives the enum values (from the file or the menus) and builds the full Employee object
    public static Employee createEmployee(Profession profession, String name, Department departmentType,
            ManagerType managerType) {
        HospitalDepartment department = DepartmentFactory.create(departmentType); // Clinical / Diagnostic / Support
        Position position = PositionFactory.create(managerType);                   // Executive / Management / Staff

        switch (profession) {
            case DOCTOR -> {
                return new Doctor(name, department, position);
            }
            case NURSE -> {
                return new Nurse(name, department, position);
            }
            case TECHNICIAN -> {
                return new Technician(name, department, position);
            }
            default -> // only reached if a new profession is added to the enum but not here
                throw new IllegalArgumentException("Invalid employee type: " + profession);
        }

    }
}
