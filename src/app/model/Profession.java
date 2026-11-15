package app.model;

// Professions; each one matches an Employee subclass (see EmployeeFactory)
public enum Profession {
    DOCTOR(1, "Doctor"),
    NURSE(2, "Nurse"),
    TECHNICIAN(3, "Technician");

    private final int professionNumber;
    private final String professionName;

    Profession(int professionNumber, String professionName) {
        this.professionNumber = professionNumber;
        this.professionName = professionName;
    }

    public int getProfessionNumber() {
        return professionNumber;
    }

    public String getProfessionName() {
        return professionName;
    }

    // finds the profession for a menu number, or null if the number doesn't exist
    public static Profession fromProfessionNumber(int professionNumber) {

        for (Profession profession : Profession.values()) {
            if (profession.getProfessionNumber() == professionNumber) {
                return profession;
            }
        }
        return null;
    }

    // finds the profession for a name from the file (ignoring case), or null if unknown
    public static Profession fromProfessionName(String professionName) {

        for (Profession profession : Profession.values()) {
            if (profession.getProfessionName().equalsIgnoreCase(professionName)) {
                return profession;
            }
        }
        return null;
    }
}
