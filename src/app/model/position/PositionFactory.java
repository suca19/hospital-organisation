package app.model.position;

import app.model.ManagerType;

// The only class that decides which level each manager type belongs to
public class PositionFactory {

    public static Position create(ManagerType type) {
        switch (type) {
            case HOSPITAL_DIRECTOR -> {
                return new ExecutivePosition(type);
            }
            case HEAD_OF_DEPARTMENT, SENIOR_MANAGER, TEAM_LEAD -> {
                return new ManagementPosition(type);
            }
            case STAFF -> {
                return new StaffPosition(type);
            }
            default -> // only reached if a new manager type is added to the enum but not here
                throw new IllegalArgumentException("Unknown manager type: " + type);
        }
    }
}
