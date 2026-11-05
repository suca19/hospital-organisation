package app.model;

import app.model.types.Doctor;
import app.model.types.Nurse;
import app.model.types.Technician;

public class EmployeeFactory {
    public static Employee createEmployee(Profession profession, String name, Department department,
            ManagerType managerType) {
        switch (profession) {
            case DOCTOR -> {
                return new Doctor(name, department, managerType);
            }
            case NURSE -> {
                return new Nurse(name, department, managerType);
            }

            case TECHNICIAN -> {
                return new Technician(name, department, managerType);
            }
            default ->
                throw new IllegalArgumentException("Invalid employee type: " + profession);
        }

    }
}
