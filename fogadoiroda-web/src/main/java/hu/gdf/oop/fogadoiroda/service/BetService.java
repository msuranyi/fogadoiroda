package hu.gdf.oop.fogadoiroda.service;

/**
 * A fogadásokkal kapcsolatos üzleti logikát leíró interfész.
 */
public interface BetService {

    /**
     * Egy esemény egy konkrét kimenetelére történő fogadás.
     *
     * @param outcome az adott esemény kimenetelei közül annak az azonosítója, amelyre a fogadást teszik
     */
    void bet(Integer outcome, int betAmount);

    /**
     * Egy eseményre leadott tipp törlése.
     *
     * @param event annak az eseménynek az azonosítója, amelyre a leadott tippet törölni akarják
     */
    void delete(Integer event);
}
