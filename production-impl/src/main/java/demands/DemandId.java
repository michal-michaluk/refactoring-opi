package demands;

import java.time.LocalDate;

public class DemandId {
    private final String productRefNo;
    private final LocalDate atDay;

    public DemandId(String productRefNo, LocalDate atDay) {
        this.productRefNo = productRefNo;
        this.atDay = atDay;
    }

    public String getProductRefNo() {
        return productRefNo;
    }

    public LocalDate getAtDay() {
        return atDay;
    }
}
