package demands;

import enums.DeliverySchema;

public class DemandChanged {
    private final DemandId id;
    private final long level;
    private final DeliverySchema deliverySchema;

    public DemandChanged(DemandId id, long level, DeliverySchema deliverySchema) {
        this.id = id;
        this.level = level;
        this.deliverySchema = deliverySchema;
    }

    public DemandId getId() {
        return id;
    }

    public long getLevel() {
        return level;
    }

    public DeliverySchema getDeliverySchema() {
        return deliverySchema;
    }
}
