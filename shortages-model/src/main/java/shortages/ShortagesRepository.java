package shortages;

public interface ShortagesRepository {
    Shortages get(String refNo);

    void save(Shortages shortages);

    void delete(String refNo);
}
