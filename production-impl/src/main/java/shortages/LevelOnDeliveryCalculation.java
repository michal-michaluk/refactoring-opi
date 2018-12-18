package shortages;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

interface Strategy {

    Strategy atDayStart = (long level, Demands.DailyDemand demand, long produced) -> level - demand.getLevel();
    Strategy tillEndOfDay = (long level, Demands.DailyDemand demand, long produced) -> level - demand.getLevel() + produced;
    Strategy every3hours = (long level, Demands.DailyDemand demand, long produced) -> {throw new NotImplementedException();};

    long calculateLevelOnDelivery(long level, Demands.DailyDemand demand, long produced);
}
