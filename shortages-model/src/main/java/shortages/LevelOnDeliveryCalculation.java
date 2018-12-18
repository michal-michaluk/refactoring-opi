package shortages;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface LevelOnDeliveryCalculation {

    LevelOnDeliveryCalculation atDayStart = (long level, Demands.DailyDemand demand, long produced) -> level - demand.getLevel();
    LevelOnDeliveryCalculation tillEndOfDay = (long level, Demands.DailyDemand demand, long produced) -> level - demand.getLevel() + produced;
    LevelOnDeliveryCalculation every3hours = (long level, Demands.DailyDemand demand, long produced) -> {throw new NotImplementedException();};

    long calculateLevelOnDelivery(long level, Demands.DailyDemand demand, long produced);
}
