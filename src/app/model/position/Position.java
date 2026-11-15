package app.model.position;

import app.model.ManagerType;

// Parent class for all positions: wraps the ManagerType enum and adds behaviour per level
public abstract class Position {

    private final ManagerType type; // which role this is (e.g. TEAM_LEAD)

    public Position(ManagerType type) {
        this.type = type;
    }

    public ManagerType getType() {
        return type;
    }

    public String getManagerTypeName() {
        return type.getManagerTypeName();
    }

    // seniority used to build the hierarchy tree (1 = most senior)
    public int getRank() {
        return type.getRank();
    }

    // what this level is responsible for in the hospital
    public abstract String getResponsibility();

    // whether people at this level manage other employees
    public abstract boolean canManageOthers();
}
