package hu.gdf.oop.fogadoiroda.model;

/**
 * Absztrakt ősosztály azoknak a modell osztályoknak, amelyek Repository komponensek készültek.
 */
public abstract class BaseEntity {

    /**
     * Az entitás azonosítóját visszaadó metódus.
     *
     * @return az entitás azonosítója
     */
    public abstract String getIdentifier();
}
