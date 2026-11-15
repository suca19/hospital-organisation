package app.model.position;

import app.model.ManagerType;

// Top level of the hospital (Hospital Director)
public class ExecutivePosition extends Position {

    public ExecutivePosition(ManagerType type) {
        super(type);
    }

    @Override
    public String getResponsibility() {
        return "Runs the whole hospital";
    }

    @Override
    public boolean canManageOthers() {
        return true;
    }
}
