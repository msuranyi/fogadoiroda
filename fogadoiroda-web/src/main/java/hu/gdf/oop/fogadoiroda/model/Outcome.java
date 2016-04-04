package hu.gdf.oop.fogadoiroda.model;

import javax.validation.constraints.NotNull;

/**
 * Az esemény kimenetét reprezentáló modell osztály.
 */
public class Outcome {

    private Integer id;

    private Event parent;

    private String text;

    private Boolean won;

    /**
     * Kimenet objektumot létrehozó konstruktor.
     */
    public Outcome(){
        this.id = SequenceGenerator.next();
    }

    /**
     * Kimenetel objektumot létrehozó konstruktor.
     *
     * @param parent az esemény, amelyhez tartozik a kimenet
     * @param text a kimenet szöveges leírása
     */
    public Outcome(Event parent, String text) {
        this.id = SequenceGenerator.next();
        this.parent = parent;
        this.text = text;
        this.won = false;
    }

    /**
     * A kimenet azonosítóját visszaadó metódus.
     *
     * @return a kimenet azonosítója
     */
    public Integer getId() {
        return id;
    }

    /**
     * A kimenet azonosítóját beállító metódus.
     *
     * @param id a kimenet azonosítója
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * A kimenet eseményét visszaadó metódus.
     *
     * @return a kimenet eseménye
     */
    public Event getParent() {
        return parent;
    }

    /**
     * A kimenet eseményét beállító metódus.
     *
     * @param parent a kimenet eseménye
     */
    public void setParent(Event parent) {
        this.parent = parent;
    }

    /**
     * A kimenet leírását visszaadó metódus.
     *
     * @return a kimenet leírása
     */
    @NotNull
    public String getText() {
        return text;
    }

    /**
     * A kimenet leírását beállító metódus.
     *
     * @param text a kimenet leírása
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * A kimenet eredményét (nyert/vesztett) visszaadó metódus.
     *
     * @return a kimenet eredménye
     */
    public Boolean getWon() {
        return won;
    }

    /**
     * A kimenet eredményét beállító metódus.
     *
     * @param won a kimenet eredménye
     */
    public void setWon(Boolean won) {
        this.won = won;
    }

    /**
     * Megmondja egy kimenetről, hogy megtörtént-e a kiértékelése.
     *
     * @return true, ha már ki van értékelve
     */
    public boolean isEvaluated() {
        return this.won != null;
    }
}
