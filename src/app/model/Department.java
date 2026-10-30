package app.model;

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

    public static Department fromDepartmentNumber(int departmentNumber) {

        for (Department department : Department.values()) {
            if (department.getDepartmentNumber() == departmentNumber) {
                return department;
            }
        }
        return null;
    }

    public static Department fromDepartmentName(String departmentName) {

        for (Department department : Department.values()) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                return department;
            }
        }
        return null;
    }
}
