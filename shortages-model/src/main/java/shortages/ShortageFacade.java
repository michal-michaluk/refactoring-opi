package shortages;

public class ShortageFacade {

    private ShortageCalculatorProvider provider;

    public Shortages findShortages(String refNo) {
        ShortageCalculator calculator = provider.get(refNo);
        return calculator.findShortages();
    }
}