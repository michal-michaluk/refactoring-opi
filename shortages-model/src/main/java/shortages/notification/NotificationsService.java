package shortages.notification;

import shortages.Shortages;

public interface NotificationsService {
    void alertPlanner(Shortages shortages);

    void markOnPlan(Shortages shortages);
}
