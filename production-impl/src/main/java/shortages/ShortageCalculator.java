package shortages;

import entities.ShortageEntity;
import external.CurrentStock;

import java.time.LocalDate;
import java.util.List;

public class ShortageCalculator {
    private String productRefNo;
    private CurrentStock stock;
    private List<LocalDate> dates;
    private ProductionsOutputs outputs;
    private Demands demandsPerDay;

    public ShortageCalculator(String productRefNo, CurrentStock stock, List<LocalDate> dates, ProductionsOutputs outputs, Demands demandsPerDay) {
        this.productRefNo = productRefNo;
        this.stock = stock;
        this.dates = dates;
        this.outputs = outputs;
        this.demandsPerDay = demandsPerDay;
    }

    public List<ShortageEntity> findShortages() {
        long level = stock.getLevel();

        Shortages gap = new Shortages(productRefNo);
        for (LocalDate day : dates) {
            Demands.DailyDemand demand = demandsPerDay.get(day);
            if (demand == null) {
                level += outputs.getOutput(day);
                continue;
            }
            long produced = outputs.getOutput(day);

            long levelOnDelivery = demand.calculateLevelOnDelivery(level, produced);

            if (levelOnDelivery < 0) {
                gap.add(day, levelOnDelivery);
            }
            long endOfDayLevel = level + produced - demand.getLevel();
            level = endOfDayLevel >= 0 ? endOfDayLevel : 0;
        }
        return gap.build();
    }
}
