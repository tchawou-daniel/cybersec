package com.cybersecu.secu.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="t_tasks")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_created;
    private boolean finished;

    public Task(){}

    public Task(String name, String description, Date date_created, boolean finished) {
        super();
        this.name = name;
        this.description = description;
        this.date_created = date_created;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateCreated() {
        return date_created;
    }
    public void setDateCreated(Date date_created) {
        this.date_created = date_created;
    }
    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", description=" + description + ", date_created=" + date_created
                + ", finished=" + finished + "]";
    }

}
