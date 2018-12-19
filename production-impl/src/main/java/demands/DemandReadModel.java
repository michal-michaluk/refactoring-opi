package demands;

import enums.DeliverySchema;

import java.time.LocalDate;

public class DemandReadModel {
    private final long level;
    private final DeliverySchema deliverySchema;
    private final LocalDate date;

    public DemandReadModel(long level, DeliverySchema deliverySchema, LocalDate date) {
        this.level = level;
        this.deliverySchema = deliverySchema;
        this.date = date;
    }

    public LocalDate getDay() {
        return date;
    }

    public long getLevel() {
        return level;
    }

    public DeliverySchema getDeliverySchema() {
        return deliverySchema;
    }
}
