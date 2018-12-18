package shortages;

import dao.DemandDao;
import entities.DemandEntity;
import enums.DeliverySchema;
import tools.Util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemandProvider {
    private DemandDao demandDao;
    private Map<DeliverySchema, LevelOnDeliveryCalculation> mapping = init();

    public Demands createDemands(String productRefNo, LocalDate today) {
        List<DemandEntity> demands = demandDao.findFrom(today.atStartOfDay(), productRefNo);
        return new Demands(demands.stream().collect(Collectors.toMap(
                DemandEntity::getDay,
                demand -> new Demands.DailyDemand(
                        Util.getLevel(demand),
                        pickVariant(Util.getDeliverySchema(demand))
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
