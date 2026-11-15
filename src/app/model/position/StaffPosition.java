package app.model.position;

import app.model.ManagerType;

// Front-line employees who do not manage others (Staff)
public class StaffPosition extends Position {

    public StaffPosition(ManagerType type) {
        super(type);
    }

    @Override
    public String getResponsibility() {
        return "Delivers day-to-day patient care and support";
    }

    @Override
    public boolean canManageOthers() {
        return false;
    }
}
