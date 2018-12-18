package shortages;

import java.time.LocalDate;
import java.util.Map;

public class ProductionsOutputs {

    private final Map<LocalDate, Long> outputs;

    public ProductionsOutputs(Map<LocalDate, Long> outputs) {
        this.outputs = outputs;
    }

    public long getOutput(LocalDate day) {
        return outputs.getOrDefault(day, 0L);
    }
}
