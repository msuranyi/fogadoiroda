package hu.gdf.oop.fogadoiroda.model;

public class Outcome {

    private Integer id;

    private Event parent;

    private String name;

    private Boolean won;

    public Outcome(Event parent, String name) {
        this.id = SequenceGenerator.next();
        this.parent = parent;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }
}
