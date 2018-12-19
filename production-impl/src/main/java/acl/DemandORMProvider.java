package acl;

import demands.DemandReadModel;
import demands.DemandReadModelDao;
import enums.DeliverySchema;
import shortages.DemandProvider;
import shortages.Demands;
import shortages.LevelOnDeliveryCalculation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemandORMProvider implements DemandProvider {

    private DemandReadModelDao readModel;
    private Map<DeliverySchema, LevelOnDeliveryCalculation> mapping = init();

    @Override
    public Demands createDemands(String productRefNo, LocalDate today) {
        List<DemandReadModel> demands = readModel.findFrom(today.atStartOfDay(), productRefNo);

        return new Demands(demands.stream()
                .collect(Collectors.toMap(
                        DemandReadModel::getDay,
                        demand -> new Demands.DailyDemand(
                                demand.getLevel(),
                                pickVariant(demand.getDeliverySchema())
                        )
                )));
    }

    private LevelOnDeliveryCalculation pickVariant(DeliverySchema deliverySchema) {
        return mapping.getOrDefault(deliverySchema, LevelOnDeliveryCalculation.every3hours);
    }

    private Map<DeliverySchema, LevelOnDeliveryCalculation> init() {
        Map<DeliverySchema, LevelOnDeliveryCalculation> mapping = new HashMap<>();
        mapping.put(DeliverySchema.atDayStart, LevelOnDeliveryCalculation.atDayStart);
        mapping.put(DeliverySchema.tillEndOfDay, LevelOnDeliveryCalculation.tillEndOfDay);
        return mapping;
    }
}
