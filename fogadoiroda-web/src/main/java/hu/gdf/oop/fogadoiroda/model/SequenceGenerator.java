package hu.gdf.oop.fogadoiroda.model;

/**
 * Memóriában történő tároláshoz szekvencia generátor osztály.
 */
public class SequenceGenerator {

    /**
     * Globális szekvenciaérték.
     */
    private static int SEQ = 0;

    /**
     * Egy új sorszám tépése a globális szekvenciából.
     * A metódus garantáltan egyedi sorszámot oszt minden hívásnál.
     *
     * @return az új sorszám
     */
    public static synchronized Integer next() {
        SEQ++;
        return new Integer(SEQ);
    }
}
