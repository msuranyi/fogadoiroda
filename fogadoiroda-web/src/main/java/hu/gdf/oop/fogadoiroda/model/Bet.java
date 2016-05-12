package hu.gdf.oop.fogadoiroda.model;

public class Bet extends BaseEntity{

    int userId;

    int eventId;

    int outcomeId;

    int betAmount;

    public Bet(){}

    public Bet(int userId, int eventId, int outcomeId, int betAmount){
        this.userId = userId;
        this.eventId = eventId;
        this.outcomeId = outcomeId;
        this.betAmount = betAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(int outcomeId) {
        this.outcomeId = outcomeId;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public String getIdentifier() {
        return null;
    }
}
