package hu.gdf.oop.fogadoiroda.model;

import javax.validation.constraints.NotNull;

public class Outcome {

    private Integer id;

    private Event parent;

    private String text;

    private Boolean won;

    public Outcome(){
        this.id = SequenceGenerator.next();
    }

    public Outcome(Event parent, String text) {
        this.id = SequenceGenerator.next();
        this.parent = parent;
        this.text = text;
        this.won = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getParent() {
        return parent;
    }

    public void setParent(Event parent) {
        this.parent = parent;
    }

    @NotNull
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public boolean isEvaluated() {
        return this.won != null;
    }
}
