package hu.gdf.oop.fogadoiroda.web.model;

import javax.validation.constraints.NotNull;

public class Bet {

    private Integer eventId;

    private Integer outcomeId;

    private int betAmount;

    @NotNull
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @NotNull
    public Integer getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Integer outcomeId) {
        this.outcomeId = outcomeId;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
}
