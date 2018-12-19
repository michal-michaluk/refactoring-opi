package demands;

import dao.DemandDao;
import entities.DemandEntity;

public class DemandRepository {
    private DemandDao demandDao;
    private DemandsEvents events;
    private HardcodedDeliverySchemaPolicy policy = new HardcodedDeliverySchemaPolicy();

    public DemandObject get(DemandId demandId) {
        DemandEntity demand = demandDao.getCurrent(demandId.getProductRefNo(), demandId.getAtDay());
        return new DemandObject(demandId, demand, events, policy);
    }
}
