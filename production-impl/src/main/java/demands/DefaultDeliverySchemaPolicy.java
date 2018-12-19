package demands;

import enums.DeliverySchema;

public interface DefaultDeliverySchemaPolicy {

    DeliverySchema defaultFor(String productRefNo);
}
