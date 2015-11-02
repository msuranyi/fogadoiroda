package hu.gdf.oop.fogadoiroda.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private Integer id;

    private String title;

    private String description;

    private String result;

    private Date start;

    private Date end;

    private List<Outcome> outcomes = new ArrayList<>();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
}