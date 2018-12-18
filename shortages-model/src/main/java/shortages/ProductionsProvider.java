package shortages;

import java.time.LocalDate;

public interface ProductionsProvider {
    ProductionsOutputs createProductionOutputs(String productRefNo, LocalDate today);
}
