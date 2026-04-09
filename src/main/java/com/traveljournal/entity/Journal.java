package com.traveljournal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private String title;
    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "weather")
    private String weather;

    public Journal() {}

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }

    @Override
    public String toString() {
        return "Journal{" + "id=" + id + ", title='" + title + '\'' + ", weather='" + weather + '\'' + '}';
    }
}