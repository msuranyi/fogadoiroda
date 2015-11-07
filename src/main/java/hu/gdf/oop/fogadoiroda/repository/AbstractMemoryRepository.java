package hu.gdf.oop.fogadoiroda.repository;

import hu.gdf.oop.fogadoiroda.model.BaseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMemoryRepository<E extends BaseEntity> {

    protected Map<String, E> entities = new HashMap<>();

    public Collection<E> findAll() {
        return entities.values();
    }

    public void create(E entity) {
        preCreate(entity);
        entities.put(entity.getIdentitfier(), entity);
    }

    public void update(E entity) {
        preUpdate(entity);
        entities.put(entity.getIdentitfier(), entity);
    }

    public void delete(E entity) {
        preDelete(entity);
        entities.remove(entity.getIdentitfier());
    }

    protected void preCreate(E entity) {
        checkNotNull(entity);
        checkNotExists(entity.getIdentitfier());
    }

    protected void preUpdate(E entity) {
        checkNotNull(entity);
        checkExists(entity.getIdentitfier());
    }

    protected void preDelete(E entity) {
        checkNotNull(entity);
        checkExists(entity.getIdentitfier());
    }

    private void checkNotNull(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!");
        }
    }

    private void checkNotExists(String identifier) {
        if (entities.containsKey(identifier)) {
            throw new IllegalArgumentException("Entity [" + identifier + "] already exsists");
        }
    }

    private void checkExists(String identifier) {
        if (!entities.containsKey(identifier)) {
            throw new IllegalArgumentException("Entity [" + identifier + "] not found");
        }
    }
}
