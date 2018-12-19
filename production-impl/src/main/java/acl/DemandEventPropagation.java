package acl;

import demands.DemandChanged;
import demands.DemandReadModelProjection;
import demands.DemandsEvents;
import external.ShortageProcessing;

public class DemandEventPropagation implements DemandsEvents {

    private DemandReadModelProjection projection;
    private ShortageProcessing shortages;

    @Override
    public void emit(DemandChanged event) {
        projection.applyDemandChangedEvent(event);
        shortages.processShortages(event.getId().getProductRefNo());
    }
}
