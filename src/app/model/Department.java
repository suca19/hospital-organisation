package app.model;

// Hospital departments
public enum Department {
    EMERGENCY(1, "Emergency"),
    CARDIOLOGY(2, "Cardiology"),
    PAEDIATRICS(3, "Paediatrics"),
    SURGERY(4, "Surgery"),
    RADIOLOGY(5, "Radiology"),
    LABORATORY(6, "Laboratory"),
    PHARMACY(7, "Pharmacy"),
    ADMINISTRATION(8, "Administration");

    private final int departmentNumber;
    private final String departmentName;

    Department(int departmentNumber, String departmentName) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    // finds the department for a menu number, or null if the number doesn't exist
    public static Department fromDepartmentNumber(int departmentNumber) {

        for (Department department : Department.values()) {
            if (department.getDepartmentNumber() == departmentNumber) {
                return department;
            }
        }
        return null;
    }

    // finds the department for a name from the file (ignoring case), or null if unknown
    public static Department fromDepartmentName(String departmentName) {

        for (Department department : Department.values()) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                return department;
            }
        }
        return null;
    }
}
