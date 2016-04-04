package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.BaseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository műveleteket implementáló (a tárolást memóriában megoldva) osztályokhoz absztrakt ősosztály.
 *
 * @param <E> a tárolandó entitás típusa
 */
public abstract class AbstractMemoryRepository<E extends BaseEntity> implements BaseRepository<E> {

    /**
     * A tárolt egyedeket tartalmazó Map objektum.
     */
    protected Map<String, E> entities = new HashMap<>();

    @Override
    public Collection<E> findAll() {
        return entities.values();
    }

    @Override
    public void create(E entity) {
        preCreate(entity);
        entities.put(entity.getIdentifier(), entity);
    }

    @Override
    public void update(E entity) {
        preUpdate(entity);
        entities.put(entity.getIdentifier(), entity);
    }

    @Override
    public void delete(E entity) {
        preDelete(entity);
        entities.remove(entity.getIdentifier());
    }

    /**
     * Létrehozás előtt futó metódus, ellenőrzési célokra szolgál.
     * Speciális esetben a konkrét implementációban felülírható.
     *
     * @param entity a létrehozandó egyed
     */
    protected void preCreate(E entity) {
        checkNotNull(entity);
        checkNotExists(entity.getIdentifier());
    }

    /**
     * Módosítás előtt futó metódus, ellenőrzési célokra szolgál.
     * Speciális esetben a konkrét implementációban felülírható.
     *
     * @param entity a módosítandó egyed
     */
    protected void preUpdate(E entity) {
        checkNotNull(entity);
        checkExists(entity.getIdentifier());
    }

    /**
     * Törlés előtt futó metódus, ellenőrzési célokra szolgál.
     * Speciális esetben a konkrét implementációban felülírható.
     *
     * @param entity a törlendő egyed
     */
    protected void preDelete(E entity) {
        checkNotNull(entity);
        checkExists(entity.getIdentifier());
    }

    /**
     * A metódus azt ellenőrzi, hogy egy adott egyed objektum ne legyen null,
     * ellenkező esetben {@IllegalArgumentException}-t dob.
     *
     * @param entity
     */
    private void checkNotNull(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!");
        }
    }

    /**
     * A metódus azt ellenőrzi, hogy egy adott azonosítóval ne létezzen tárolt egyed,
     * ellenkező esetben {@IllegalArgumentException}-t dob.
     *
     * @param identifier az ellenőrizendő azonosító
     */
    private void checkNotExists(String identifier) {
        if (entities.containsKey(identifier)) {
            throw new IllegalArgumentException("Entity [" + identifier + "] already exsists");
        }
    }

    /**
     * A metódus azt ellenőrzi, hogy egy adott azonosítóval létezik-e tárolt egyed,
     * ellenkező esetben {@IllegalArgumentException}-t dob.
     *
     * @param identifier az ellenőrizendő azonosító
     */
    private void checkExists(String identifier) {
        if (!entities.containsKey(identifier)) {
            throw new IllegalArgumentException("Entity [" + identifier + "] not found");
        }
    }
}
