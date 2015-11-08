package hu.gdf.oop.fogadoiroda.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Az eseményt reprezentáló modell osztály.
 */
public class Event extends BaseEntity {

    private Integer id;

    private String title;

    private String description;

    private String result;

    private Date start;

    private Date end;

    private String endTime;

    private Map<Integer, Outcome> outcomes = new HashMap<>();

    /**
     * Esemény objektumot létrehozó konstruktor.
     */
    public Event() {
        this.id = SequenceGenerator.next();
    }

    /**
     * Esemény objektumot létrehozó konstruktor.
     *
     * @param title esemény rövid címe
     * @param description esemény leírása
     * @param start esemény létrehozásának dátuma
     * @param end esemény lezárásának dátuma
     */
    public Event(String title, String description, Date start, Date end) {
        this.id = SequenceGenerator.next();
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String getIdentifier() {
        return id != null ? id.toString() : null;
    }

    /**
     * Az esemény azonosítóját visszaadó metódus.
     *
     * @return az esemény azonosítója
     */
    public Integer getId() {
        return id;
    }

    /**
     * Az esemény azonosítóját beállító metódus.
     *
     * @param id az esemény azonosítója
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Az esemény rövid címét visszaadó metódus.
     *
     * @return az esemény rövid címe
     */
    @NotNull
    public String getTitle() {
        return title;
    }

    /**
     * Az esemény rövid címét beállító metódus.
     *
     * @param title az esemény rövid címe
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Az esemény leírását visszaadó metódus.
     *
     * @return az esemény leírása
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * Az esemény leírását beállító metódus.
     *
     * @param description az esemény leírása
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Az esemény eredményét visszaadó metódus.
     *
     * @return az esemény eredménye
     */
    public String getResult() {
        return result;
    }

    /**
     * Az esemény eredményét beállító metódus.
     *
     * @param result az esemény eredménye
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Az esemény létrehozásának dátumát visszaadó metódus.
     *
     * @return az esemény létrehozásának dátuma
     */
    public Date getStart() {
        return start;
    }

    /**
     * Az esemény létrehozásának dátumát beállító metódus.
     *
     * @param start az esemény létrehozásának dátuma
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Az esemény lezárásának dátumát visszaadó metódus.
     *
     * @return az esemény lezárásának dátuma
     */
    @NotNull
    public Date getEnd() {
        return end;
    }

    /**
     * Az esemény lezárásának dátumát beállító metódus.
     *
     * @param end az esemény lezárásának dátuma
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Az esemény lezárásának óra:perc értékét visszaadó metódus.
     *
     * @return az esemény óra:perc értéke
     */
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
    @NotNull
    public String getEndTime() {
        return endTime;
    }

    /**
     * Az esemény óra:perc értékét beállító metódus.
     *
     * @param endTime az esemény óra:perc értéke
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Az esemény kimeneteit visszaadó metódus.
     *
     * @return az esemény kimenetei
     */
    public Map<Integer, Outcome> getOutcomes() {
        return outcomes;
    }

    /**
     * Az esemény kimeneteit beállító metódus.
     *
     * @param outcomes az esemény kimenetei
     */
    public void setOutcomes(Map<Integer, Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    /**
     * Megmondja egy eseményről, hogy lezárult-e már a fogadások szempontjából.
     *
     * @return lezárt esetén true
     */
    public boolean isClosed() {
        boolean result = true;
        if (end == null || end.after(new Date())) {
            result = false;
        }
        return result;
    }
}
