package shortages;

import dao.ProductionDao;
import entities.ProductionEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProductionsProvider {

    private ProductionDao productionDao;

    public ProductionsOutputs createProductionOutputs(String productRefNo, LocalDate today) {
        List<ProductionEntity> productions = productionDao.findFromTime(productRefNo, today.atStartOfDay());
        return new ProductionsOutputs(
                productions.stream().collect(Collectors.groupingBy(
                        p -> p.getStart().toLocalDate(),
                        Collectors.summingLong(ProductionEntity::getOutput)
                ))
        );
    }
}
