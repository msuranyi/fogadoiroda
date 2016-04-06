package hu.gdf.oop.fogadoiroda.data.entity;

public class Outcome {
    private Integer id;
    private Integer betEventId;
    private String title;
    private boolean won;
    private Integer sumBetAmount;

    public Integer getId() {
        return id;
    }

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
    
    public Object[] toArray(){
        Object[] array = {id,betEventId,title,won,sumBetAmount};
        return array;
    }

    public static Object[] getEmptyModel(){
        return new Object[5];
    }
}
