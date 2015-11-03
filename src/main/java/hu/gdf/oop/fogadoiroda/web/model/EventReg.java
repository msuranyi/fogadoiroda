package hu.gdf.oop.fogadoiroda.web.model;

import hu.gdf.oop.fogadoiroda.model.Event;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class EventReg {

    private String title;

    private String description;

    private Date end;

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

    @NotNull
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Event getInstance(){ return new Event(title,description,new Date(),end); }
}
