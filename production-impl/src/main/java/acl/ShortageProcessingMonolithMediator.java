package acl;

import entities.ProductionEntity;
import external.ShortageProcessing;
import shortages.ShortageMonitoringProcess;

import java.util.List;
import java.util.stream.Collectors;

public class ShortageProcessingMonolithMediator implements ShortageProcessing {

    private ShortageMonitoringProcess shortages;

    @Override
    public void processShortages(String productRefNo) {
        shortages.processShortages(productRefNo);
    }

    @Override
    public void processShortages(List<ProductionEntity> changed) {
        shortages.processShortages(changed.stream()
                .map(production -> production.getForm().getRefNo())
                .collect(Collectors.toList())
        );
    }
}
