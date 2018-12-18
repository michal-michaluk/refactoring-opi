package shortages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shortages {
    private final CurrentStock stock;
    private final String refNo;
    private final List<ShortageEntity> list = new ArrayList<>();

    public Shortages(CurrentStock stock, String refNo) {
        this.stock = stock;
        this.refNo = refNo;
    }

    public void add(LocalDate day, long levelOnDelivery) {
        ShortageEntity entity = new ShortageEntity();
        entity.setRefNo(refNo);
        entity.setFound(LocalDate.now());
        entity.setMissing(Math.abs(levelOnDelivery));
        entity.setAtDay(day);
        list.add(entity);
    }

    public List<ShortageEntity> build() {
        return list;
    }

    public boolean foundDifferent(Shortages other) {
        return false;
    }

    public boolean anyLocked() {
        return stock.getLocked() > 0;
    }

    public boolean firstBefore(LocalDate date) {
        return list.get(0).getAtDay().isBefore(date);
    }

    public boolean resolved(Shortages previous) {
        return list.isEmpty() && !previous.list.isEmpty();
    }
}
