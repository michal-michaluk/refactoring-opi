package shortages;

import shortages.notification.JiraService;
import shortages.notification.NotificationsService;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class ShortageMonitoringProcess {

    private ShortagesRepository repository;

    private ShortageFacade shortagesService;
    private NotificationsService notificationService;
    private JiraService jiraService;
    private Clock clock;

    private long confIncreaseQATaskPriorityInDays;

    public void processShortages(String productRefNo) {
        process(LocalDate.now(clock), productRefNo, notificationService::alertPlanner);
    }

    public void processShortages(List<String> refNos) {
        LocalDate today = LocalDate.now(clock);
        for (String refNo : refNos) {
            process(today, refNo, notificationService::markOnPlan);
        }
    }

    private void process(LocalDate today, String refNo, Consumer<Shortages> notification) {
        Shortages shortages = shortagesService.findShortages(refNo);

        Shortages previous = repository.get(refNo);
        if (shortages.foundDifferent(previous)) {
            notification.accept(shortages);
            if (shortages.anyLocked() &&
                    shortages.firstBefore(today.plusDays(confIncreaseQATaskPriorityInDays))) {
                jiraService.increasePriorityFor(refNo);
            }
            repository.save(shortages);
        }
        if (shortages.resolved(previous)) {
            repository.delete(refNo);
        }
    }
}
