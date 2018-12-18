package shortages;

import entities.ShortageEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shortages {
    private final String refNo;
    private final List<ShortageEntity> list = new ArrayList<>();

    public Shortages(String refNo) {
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
}
