package services.impl;

public interface ProductionEvents {
    void emit(ProductionChanged event);
}
