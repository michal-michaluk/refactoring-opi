package shortages;

import java.time.LocalDate;

public interface DemandProvider {
    Demands createDemands(String productRefNo, LocalDate today);
}
