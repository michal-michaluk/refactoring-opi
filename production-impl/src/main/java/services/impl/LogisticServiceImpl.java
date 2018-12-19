package services.impl;

import api.AdjustDemandDto;
import api.LogisticService;
import api.StockForecastDto;
import demands.DemandId;
import demands.DemandObject;
import demands.DemandRepository;

import java.time.Clock;
import java.time.LocalDate;

public class LogisticServiceImpl implements LogisticService {

    //Inject all
    private DemandRepository repository;
    private Clock clock;

    /**
     * <pre>
     * Adjust demand at day to amount, delivered.
     *  New demand is stored for further reference
     *   We can change only Demands for today and future.
     *  Data from callof document should be preserved in database (DON’T OVERRIDE THEM).
     *   Should be possible to adjust demand even
     *  if there was no callof document for that product.
     *    Logistician note should be kept along with adjustment.
     *  If new demand is not fulfilled by  current product stock and production forecast
     *    there is a shortage in particular days and we need to rise an alert.
     *    planner should be notified,
     *    if there are locked parts on stock,
     *      QA task for recovering them should have high priority.
     * </pre>
     *
     * @param adjustment
     */
    //Transactional
    @Override
    public void adjustDemand(AdjustDemandDto adjustment) {
        if (adjustment.getAtDay().isBefore(LocalDate.now(clock))) {
            return; // TODO it is UI issue or reproduced post
        }

        DemandObject object = repository.get(getId(adjustment));
        object.adjustDemand(adjustment);
    }

    private DemandId getId(AdjustDemandDto adjustment) {
        return new DemandId(adjustment.getProductRefNo(), adjustment.getAtDay());
    }

    /**
     * <pre>
     * Daily processing of callof document:
     * for all products included in callof document
     *   New demand are stored for further reference
     *   If new demand is not fulfilled by product stock and production forecast
     *     there is a shortage in particular days and we need to rise an alert.
     *     planner should be notified in that case,
     *     if there are locked parts on stock,
     *       QA task for recovering them should have high priority.
     * </pre>
     *
     * @param document
     */
    //Transactional
    @Override
    public void processCallof(Object document) {
        // TODO implement me later
        // processShortages()

    }

    //ReadOnly
    @Override
    public StockForecastDto getStockForecast(String productRefNo) {
        return new StockForecastDto();
    }


}
