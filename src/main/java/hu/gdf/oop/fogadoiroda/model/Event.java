package hu.gdf.oop.fogadoiroda.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

public class Event {

    private Integer id;

    private String title;

    private String description;

    private String result;

    private Date start;

    private Date end;

    private String endTime;

    private Map<Integer, Outcome> outcomes = new HashMap<>();

    public Event() {
        this.id = SequenceGenerator.next();
    }

    public Event(String title, String description, Date start, Date end) {
        this.id = SequenceGenerator.next();
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Future
    @NotNull
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
    @NotNull
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Map<Integer, Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Map<Integer, Outcome> outcomes) {
        this.outcomes = outcomes;
    }

}
