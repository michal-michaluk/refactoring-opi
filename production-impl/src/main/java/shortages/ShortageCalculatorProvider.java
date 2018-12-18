package shortages;

import entities.DemandEntity;
import entities.ProductionEntity;
import external.CurrentStock;
import shortages.Demands;
import shortages.Productions;
import shortages.ShortageCalculator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ShortageCalculatorRepository {
    private LocalDate today;
    private int daysAhead;
    private CurrentStock stock;
    private List<ProductionEntity> productions;
    private List<DemandEntity> demands;

    public ShortageCalculatorRepository(LocalDate today, int daysAhead, CurrentStock stock, List<ProductionEntity> productions, List<DemandEntity> demands) {
        this.today = today;
        this.daysAhead = daysAhead;
        this.stock = stock;
        this.productions = productions;
        this.demands = demands;
    }

    public ShortageCalculator get(String productRefNo) {
        List<LocalDate> dates = Stream.iterate(today, date -> date.plusDays(1))
                .limit(daysAhead)
                .collect(toList());

        Productions outputs = new Productions(productions);
        Demands demandsPerDay = new Demands(demands);

        return new ShortageCalculator(stock, dates, outputs, demandsPerDay);
    }
}
