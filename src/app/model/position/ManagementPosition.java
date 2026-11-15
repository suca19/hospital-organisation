package app.model.position;

import app.model.ManagerType;

// Middle levels that lead people (Head of Department, Senior Manager, Team Lead)
public class ManagementPosition extends Position {

    public ManagementPosition(ManagerType type) {
        super(type);
    }

    @Override
    public String getResponsibility() {
        return "Leads a department or team";
    }

    @Override
    public boolean canManageOthers() {
        return true;
    }
}
