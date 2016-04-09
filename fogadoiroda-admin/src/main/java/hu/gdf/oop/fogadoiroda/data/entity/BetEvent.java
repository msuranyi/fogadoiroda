package hu.gdf.oop.fogadoiroda.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BetEvent implements BaseEntity {

    private Integer id;

    private Integer userId;

    private String title;

    private LocalDateTime created;

    private Integer status;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BetEvent)) return false;

        BetEvent betEvent = (BetEvent) o;

        return id != null ? id.equals(betEvent.id) : betEvent.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
