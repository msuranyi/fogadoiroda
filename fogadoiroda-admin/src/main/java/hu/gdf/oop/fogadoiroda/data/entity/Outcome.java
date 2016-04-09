package hu.gdf.oop.fogadoiroda.data.entity;

public class Outcome implements BaseEntity {

    private Integer id;

    private Integer betEventId;

    private String title;

    private boolean won;

    private Integer sumBetAmount;

    private boolean dirty;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBetEventId() {
        return betEventId;
    }

    public void setBetEventId(Integer betEventId) {
        this.betEventId = betEventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public Integer getSumBetAmount() {
        return sumBetAmount;
    }

    public void setSumBetAmount(Integer sumBetAmount) {
        this.sumBetAmount = sumBetAmount;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Outcome)) return false;

        Outcome outcome = (Outcome) o;

        return id != null ? id.equals(outcome.id) : outcome.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
