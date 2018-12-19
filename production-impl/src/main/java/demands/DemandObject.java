package demands;

import api.AdjustDemandDto;
import entities.DemandEntity;
import entities.ManualAdjustmentEntity;
import enums.DeliverySchema;

public class DemandObject {

    private DemandId id;
    private DemandEntity demand;
    private DemandsEvents events;
    private DefaultDeliverySchemaPolicy policy;

    public DemandObject(DemandId id, DemandEntity demand, DemandsEvents events, DefaultDeliverySchemaPolicy policy) {
        this.id = id;
        this.demand = demand;
        this.events = events;
        this.policy = policy;
    }

    public void adjustDemand(AdjustDemandDto adjustment) {
        ManualAdjustmentEntity manualAdjustment = new ManualAdjustmentEntity();
        manualAdjustment.setLevel(adjustment.getLevel());
        manualAdjustment.setNote(adjustment.getNote());
        manualAdjustment.setDeliverySchema(adjustment.getDeliverySchema());

        demand.getAdjustment().add(manualAdjustment);

        events.emit(new DemandChanged(id, getLevel(), getDeliverySchema()));
    }

    public void processDocument() {
        //
    }

    private long getLevel() {
        if (demand.getAdjustment().isEmpty()) {
            return demand.getOriginal().getLevel();
        } else {
            return demand.getAdjustment().get(demand.getAdjustment().size() - 1).getLevel();
        }
    }

    private DeliverySchema getDeliverySchema() {
        DeliverySchema deliverySchema;
        if (demand.getAdjustment().isEmpty()) {
            deliverySchema = demand.getOriginal().getDeliverySchema();
        } else {
            deliverySchema = demand.getAdjustment().get(demand.getAdjustment().size() - 1).getDeliverySchema();
        }
        if (deliverySchema == null) {
            return policy.defaultFor(demand.getProductRefNo());
        }
        return deliverySchema;
    }

}
