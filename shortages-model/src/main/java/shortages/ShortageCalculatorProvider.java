package shortages;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ShortageCalculatorProvider {

    private ProductionsProvider production;
    private DemandProvider demands;
    private WarehouseStock stocks;
    private Clock clock;

    private int daysAhead;

    public ShortageCalculator get(String productRefNo) {
        LocalDate today = LocalDate.now(clock);
        List<LocalDate> dates = Stream.iterate(today, date -> date.plusDays(1))
                .limit(daysAhead)
                .collect(toList());
        CurrentStock stock = stocks.getCurrentStock(productRefNo);

        Demands demandsPerDay = demands.createDemands(productRefNo, today);
        ProductionsOutputs outputs = production.createProductionOutputs(productRefNo, today);

        return new ShortageCalculator(productRefNo, stock, dates, outputs, demandsPerDay);
    }

}
