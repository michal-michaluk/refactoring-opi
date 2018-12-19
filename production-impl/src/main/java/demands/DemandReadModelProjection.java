package demands;

public class DemandReadModelProjection {

    private DemandReadModelDao readModel;

    public void applyDemandChangedEvent(DemandChanged event) {
        readModel.save(new DemandReadModel(
                event.getLevel(), event.getDeliverySchema(), event.getId().getAtDay()
        ));
    }
}
