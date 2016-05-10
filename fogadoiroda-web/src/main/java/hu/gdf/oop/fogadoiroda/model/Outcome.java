package hu.gdf.oop.fogadoiroda.model;

import javax.validation.constraints.NotNull;

/**
 * Az esemény kimenetét reprezentáló modell osztály.
 */
public class Outcome extends BaseEntity{

    private Integer id;

    private Integer eventId;

    private String title;

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
     * @param eventId az esemény azonosítója, amelyhez tartozik a kimenet
     * @param title a kimenet szöveges leírása
     */
    public Outcome(Integer eventId, String title) {
        this.id = SequenceGenerator.next();
        this.eventId = eventId;
        this.title = title;
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
     * A kimenet eseményének azonosítóját visszaadó metódus.
     *
     * @return a kimenet eseményének azonosítója
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * A kimenet eseményét beállító metódus.
     *
     * @param eventId a kimenet eseményének az azonosítója
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * A kimenet leírását visszaadó metódus.
     *
     * @return a kimenet leírása
     */
    @NotNull
    public String getTitle() {
        return title;
    }

    /**
     * A kimenet leírását beállító metódus.
     *
     * @param title a kimenet leírása
     */
    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String getIdentifier() { return id != null ? id.toString() : null; }
}
