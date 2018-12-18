package shortages;

import entities.ProductionEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Productions {

    private final Map<LocalDate, List<ProductionEntity>> outputs;
    private String productRefNo;

    public Productions(List<ProductionEntity> productions) {
        outputs = new HashMap<>();
        for (ProductionEntity production : productions) {
            if (!outputs.containsKey(production.getStart().toLocalDate())) {
                outputs.put(production.getStart().toLocalDate(), new ArrayList<>());
            }
            outputs.get(production.getStart().toLocalDate()).add(production);
            productRefNo = production.getForm().getRefNo();
        }
    }

    public long getOutput(LocalDate day) {
        List<ProductionEntity> production = outputs.get(day);
        long output = 0;
        for (ProductionEntity entity : production) {
            output = entity.getOutput();
        }
        return output;
    }

    public String getProductRefNo() {
        return productRefNo;
    }
}
