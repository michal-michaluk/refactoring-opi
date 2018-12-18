package services.impl;

import java.util.Set;

public class ProductionChanged {
    private final Set<String> changedRefNos;

    public ProductionChanged(Set<String> changedRefNos) {
        this.changedRefNos = changedRefNos;
    }

    public Set<String> getRefNos() {
        return changedRefNos;
    }
}
