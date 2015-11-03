package hu.gdf.oop.fogadoiroda.model;

import java.util.Date;

public class Bet {

    private Integer id;

    private User user;

    private Outcome outcome;

    private Date betTime;

    public Bet(User user, Outcome outcome) {
        this.id = SequenceGenerator.next();
        this.user = user;
        this.outcome = outcome;
        this.betTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

}
