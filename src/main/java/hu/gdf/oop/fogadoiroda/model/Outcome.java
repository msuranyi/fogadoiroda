package hu.gdf.oop.fogadoiroda.model;

public class Outcome {

    private Integer id;

    private Integer parent;

    private String text;

    private Boolean won;

    public Outcome(Integer parent, String text) {
        this.id = SequenceGenerator.next();
        this.parent = parent;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

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
}
