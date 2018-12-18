package shortages;

import java.time.LocalDate;
import java.util.Map;

public class Demands {

    private final Map<LocalDate, DailyDemand> demandsPerDay;

    public Demands(Map<LocalDate, DailyDemand> demands) {
        demandsPerDay = demands;
    }

    public DailyDemand get(LocalDate day) {
        return demandsPerDay.getOrDefault(day, null);
    }

    public static class DailyDemand {
        private final long level;
        private final LevelOnDeliveryCalculation strategy;

        public DailyDemand(long level, LevelOnDeliveryCalculation strategy) {
            this.level = level;
            this.strategy = strategy;
        }

        public long getLevel() {
            return level;
        }

        public long calculateLevelOnDelivery(long level, long produced) {
            return strategy
                    .calculateLevelOnDelivery(level, this, produced);
        }
    }
}
