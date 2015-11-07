package hu.gdf.oop.fogadoiroda.service;

/**
 * A fogadásokkal kapcsolatos üzleti logikát leíró interfész.
 */
public interface BetService {

    /**
     * Egy esemény egy konkrét kimenetelére történő fogadás.
     *
     * @param event annak az eseménynek az azonosítója, amelyre a fogadás történik
     * @param outcome az adott esemény kimenetelei közül annak az azonosítója, amelyre a fogadást teszik
     */
    void bet(Integer event, Integer outcome);

    /**
     * Egy eseményre leadott tipp törlése.
     *
     * @param event annak az eseménynek az azonosítója, amelyre a leadott tippet törölni akarják
     */
    void delete(Integer event);
}
