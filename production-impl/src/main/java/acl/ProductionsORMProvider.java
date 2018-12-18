package acl;

import dao.ProductionDao;
import entities.ProductionEntity;
import shortages.ProductionsOutputs;
import shortages.ProductionsProvider;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProductionsORMProvider implements ProductionsProvider {

    private ProductionDao productionDao;

    @Override
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
