package shortages;

import entities.DemandEntity;
import enums.DeliverySchema;
import tools.Util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Demands {

    private final HashMap<LocalDate, DemandEntity> demandsPerDay;

    public Demands(List<DemandEntity> demands) {
        demandsPerDay = new HashMap<>();
        for (DemandEntity demand1 : demands) {
            demandsPerDay.put(demand1.getDay(), demand1);
        }
    }

    public DailyDemand get(LocalDate day) {
        DemandEntity demand = demandsPerDay.get(day);
        if (demand != null) {
            return new DailyDemand(Util.getLevel(demand), Util.getDeliverySchema(demand));
        }
        return null;
    }

    public static class DailyDemand {
        private final long level;
        private final LevelOnDeliveryCalculation strategy;

        public DailyDemand(long level, DeliverySchema deliverySchema) {
            this.level = level;
            strategy = pickVariant(deliverySchema);
        }

        public long getLevel() {
            return level;
        }

        public long calculateLevelOnDelivery(long level, long produced) {
            return strategy
                    .calculateLevelOnDelivery(level, this, produced);
        }

        private LevelOnDeliveryCalculation pickVariant(DeliverySchema deliverySchema) {
            if (deliverySchema == DeliverySchema.atDayStart) {
                return LevelOnDeliveryCalculation.atDayStart;
            } else if (deliverySchema == DeliverySchema.tillEndOfDay) {
                return LevelOnDeliveryCalculation.tillEndOfDay;
            } else if (deliverySchema == DeliverySchema.every3hours) {
                // TODO WTF ?? we need to rewrite that app :/
                return LevelOnDeliveryCalculation.every3hours;
            } else {
                // TODO implement other variants
                return LevelOnDeliveryCalculation.every3hours;

            }
        }
    }
}
