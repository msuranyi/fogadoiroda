package hu.gdf.oop.fogadoiroda.service;

import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.repository.OutcomeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
/**
 * A kimenetelekkel kapcsolatos �zleti logik�t implementáló komponens.
 */
@Service
public class OutcomeServiceImpl implements OutcomeService{

    @Resource
    private OutcomeRepository outcomeRepository;

    @Override
    public Outcome findById(int id) {
        return outcomeRepository.findById(id);
    }

    @Override
    public void create(Outcome outcome) {
        outcomeRepository.create(outcome);
    }

    @Override
    public void update(Outcome outcome) {
        outcomeRepository.update(outcome);
    }

    @Override
    public void delete(int id) {
        Outcome outcome = outcomeRepository.findById(id);
        outcomeRepository.delete(outcome);
    }

    @Override
    public Collection<Outcome> findAll() {
        return outcomeRepository.findAll();
    }

    @Override
    public Collection<Outcome> findByBetEventId(int eventId) {
        return outcomeRepository.findByBetEventId(eventId);
    }
}
