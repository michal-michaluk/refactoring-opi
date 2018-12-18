package external;

import entities.ProductionEntity;

import java.util.List;

public interface ShortageProcessing {
    void processShortages(String productRefNo);

    void processShortages(List<ProductionEntity> changed);
}
