package app.model;

public enum ManagerType {
    HOSPITAL_DIRECTOR(1, "Hospital Director",1),
    HEAD_OF_DEPARTMENT(2, "Head of Department",2),
    SENIOR_MANAGER(3, "Senior Manager",3),
    TEAM_LEAD(4, "Team Lead",4),
    STAFF(5, "Staff",5);

    private final int managerTypeNumber;
    private final String managerTypeName;
    private final int rank;

    ManagerType(int managerTypeNumber, String managerTypeName, int rank) {
        this.managerTypeNumber = managerTypeNumber;
        this.managerTypeName = managerTypeName;
        this.rank = rank;
    }

    public int getManagerTypeNumber() {
        return managerTypeNumber;
    }

    public String getManagerTypeName() {
        return managerTypeName;
    }

    public int getRank(){
        return rank;
    }

    public static ManagerType fromManagerTypeNumber(int managerTypeNumber) {

        for (ManagerType managerType : ManagerType.values()) {
            if (managerType.getManagerTypeNumber() == managerTypeNumber) {
                return managerType;
            }

        }

        return null;
    }

    public static ManagerType fromManagerTypeName(String managerTypeName) {

        for (ManagerType managerType : ManagerType.values()) {
            if (managerType.getManagerTypeName().equalsIgnoreCase(managerTypeName)) {
                return managerType;
            }
        }

        return null;
    }
}
