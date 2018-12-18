package acl;

import services.impl.ProductionChanged;
import services.impl.ProductionEvents;
import shortages.ShortageMonitoringProcess;

import java.util.ArrayList;

public class ProductionEventsMonolithPropagation implements ProductionEvents {

    private ShortageMonitoringProcess shortageProcess;

    @Override
    public void emit(ProductionChanged event) {
        // save event

        // propagate inside monolith
        shortageProcess.processShortages(new ArrayList<>(event.getRefNos()));

        // send message over wire
    }
}
