package shortages;

import entities.ShortageEntity;

import java.time.LocalDate;
import java.util.List;

public class Shortages {
    public static Builder builder() {
        return null;
    }

    public class Builder {
        public void add(String productRefNo, LocalDate now, long levelOnDelivery, LocalDate day) {
            //levelOnDelivery * -1L;

        }

        public List<ShortageEntity> build() {
            return null;
        }
    }
}
